package com.twotoasters.sideoftoastsample;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.squareup.otto.Subscribe;
import com.twotoasters.sideoftoast.Events;
import com.twotoasters.sideoftoast.SideOfToast;
import com.twotoasters.sideoftoast.items.ToastMenuFooterItem;
import com.twotoasters.sideoftoast.items.ToastMenuItem;
import com.twotoasters.sideoftoast.mvp.BusProvider;

public class EbatesActivity extends FragmentActivity {

    public static final int EBATES_ITEM_TYPE = 0;

    public static final int FEATURED_ID = 0;
    public static final int ALL_STORES_ID = 1;
    public static final int TELL_A_FRIEND_ID = 2;
    public static final int HELP_ID = 3;
    public static final int MY_EBATES_ID = 4;
    public static final int FOOTER_ID = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BusProvider.register(this);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new EbatesFragment())
                    .commit();
        }

        addEbatesMenu();
    }

    private void addEbatesMenu() {
        ToastMenuItem featuredItem =
                new ToastMenuItem.Builder(FEATURED_ID, EBATES_ITEM_TYPE)
                        .addText(R.id.txtSidebar, R.string.ebates_item_featured)
                        .addImage(R.id.navmenuitem_icon, R.drawable.selector_ebates_menu_featured)
                        .build(this);
        ToastMenuItem allStores =
                new ToastMenuItem.Builder(ALL_STORES_ID, EBATES_ITEM_TYPE)
                        .addText(R.id.txtSidebar, R.string.ebates_item_all_stores)
                        .addImage(R.id.navmenuitem_icon, R.drawable.selector_ebates_menu_all_stores)
                        .build(this);
        ToastMenuItem tafItem =
                new ToastMenuItem.Builder(TELL_A_FRIEND_ID, EBATES_ITEM_TYPE)
                        .addText(R.id.txtSidebar, R.string.ebates_item_tell_friend)
                        .addImage(R.id.navmenuitem_icon, R.drawable.selector_ebates_menu_taf)
                        .build(this);
        ToastMenuItem helpItem =
                new ToastMenuItem.Builder(HELP_ID, EBATES_ITEM_TYPE)
                        .addText(R.id.txtSidebar, R.string.ebates_item_help)
                        .addImage(R.id.navmenuitem_icon, R.drawable.selector_ebates_menu_help)
                        .build(this);
        ToastMenuItem myEbatesItem =
                new ToastMenuItem.Builder(MY_EBATES_ID, EBATES_ITEM_TYPE)
                        .addText(R.id.txtSidebar, R.string.ebates_item_my_ebates)
                        .addImage(R.id.navmenuitem_icon, R.drawable.selector_ebates_myebates)
                        .build(this);


        ToastMenuFooterItem footer =
                new ToastMenuFooterItem.Builder(R.layout.ebates_view_sidebar_footer)
                        .setMenuId(FOOTER_ID)
                        .addText(R.id.txtSidebarName, R.string.ebates_sidebar_account_name)
                        .addText(R.id.txtSidebarCashPaidValue, "$158.22")
                        .addText(R.id.txtSidebarCashPendingValue, "$1000.00")
                        .addText(R.id.txtSidebarTotalCashValue, "$1158.22")
                        .addText(R.id.txtSidebarNextCashValue, "9/06/2014")
                        .setEnabled(false)
                        .build(this);

        SideOfToast sideOfToast = new SideOfToast.Builder()
                .addItemViewType(EBATES_ITEM_TYPE, R.layout.ebates_item_sidebar)
                .addMenuItem(featuredItem)
                .addMenuItem(allStores)
                .addMenuItem(tafItem)
                .addMenuItem(helpItem)
                .addMenuItem(myEbatesItem)
                .addFooter(footer)
                .setWidth(300)
                .setSelected(0)
                .setSlidingContent(true)
                .includeActionBarInDrawerContent(true)
                .build()
                .create(this);
    }

    @Override
    protected void onDestroy() {
        BusProvider.unregister(this);
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ebates_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sitter_city) {
            startActivity(new Intent(this, SitterCityActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }


    public static class EbatesFragment extends Fragment {

        public EbatesFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_ebates, container, false);
            ((Button) rootView.findViewById(R.id.btn_change_cash)).setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            BusProvider.post(
                                    new Events.SetResourceEvent(
                                            FOOTER_ID,
                                            R.id.txtSidebarCashPendingValue,
                                            "$1500"));
                        }
                    });

            ((Button) rootView.findViewById(R.id.btn_change_icon)).setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            BusProvider.post(
                                    new Events.SetResourceEvent(
                                            FEATURED_ID,
                                            R.id.navmenuitem_icon,
                                            R.drawable.selector_ebates_menu_help));
                        }
                    });
            return rootView;
        }
    }

    @Subscribe
    public void onToastMenuItemClick(
            Events.ToastMenuItemClickEvent event) {
        Toast.makeText(this, "Menu Item Id: " + event.getMenuId() + ", position: "
                + event.getPosition() + " clicked.", Toast.LENGTH_SHORT)
                .show();
    }
}

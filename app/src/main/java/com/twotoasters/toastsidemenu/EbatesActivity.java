package com.twotoasters.toastsidemenu;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.otto.Subscribe;
import com.twotoasters.toastnavmenu.BusProvider;
import com.twotoasters.toastnavmenu.SideOfToast;
import com.twotoasters.toastnavmenu.ToastMenuFooterItem;
import com.twotoasters.toastnavmenu.ToastMenuItem;
import com.twotoasters.toastnavmenu.mvp.NavigationDrawerFragmentViewImpl;

public class EbatesActivity extends FragmentActivity {

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
                new ToastMenuItem.Builder(0, 0)
                        .addText(R.id.txtSidebar, R.string.ebates_item_featured)
                        .addImage(R.id.navmenuitem_icon, R.drawable.selector_ebates_menu_featured)
                        .build(this);
        ToastMenuItem allStores =
                new ToastMenuItem.Builder(1, 0)
                        .addText(R.id.txtSidebar, R.string.ebates_item_all_stores)
                        .addImage(R.id.navmenuitem_icon, R.drawable.selector_ebates_menu_all_stores)
                        .build(this);
        ToastMenuItem tafItem =
                new ToastMenuItem.Builder(1, 0)
                        .addText(R.id.txtSidebar, R.string.ebates_item_tell_friend)
                        .addImage(R.id.navmenuitem_icon, R.drawable.selector_ebates_menu_taf)
                        .build(this);
        ToastMenuItem helpItem =
                new ToastMenuItem.Builder(1, 0)
                        .addText(R.id.txtSidebar, R.string.ebates_item_help)
                        .addImage(R.id.navmenuitem_icon, R.drawable.selector_ebates_menu_help)
                        .build(this);
        ToastMenuItem myEbatesItem =
                new ToastMenuItem.Builder(1, 0)
                        .addText(R.id.txtSidebar, R.string.ebates_item_my_ebates)
                        .addImage(R.id.navmenuitem_icon, R.drawable.selector_ebates_myebates)
                        .build(this);


        ToastMenuFooterItem footer =
                new ToastMenuFooterItem.Builder(R.layout.ebates_view_sidebar_footer)
                        .addText(R.id.txtSidebarName, R.string.ebates_sidebar_account_name)
                        .addText(R.id.txtSidebarCashPaidValue, "$158.22")
                        .addText(R.id.txtSidebarCashPendingValue, "$1000.00")
                        .addText(R.id.txtSidebarTotalCashValue, "$1158.22")
                        .addText(R.id.txtSidebarNextCashValue, "9/06/2014")
                        .setEnabled(false)
                        .build(this);

        SideOfToast sideOfToast = new SideOfToast.Builder(R.layout.ebates_fragment_sidebar)
                .addItemViewType(0, R.layout.ebates_item_sidebar)
                .addMenuItem(featuredItem)
                .addMenuItem(allStores)
                .addMenuItem(tafItem)
                .addMenuItem(helpItem)
                .addMenuItem(myEbatesItem)
                .addFooter(footer)
                .setWidth(300)
                .setSelected(2)
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


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class EbatesFragment extends Fragment {

        public EbatesFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    @Subscribe
    public void onToastMenuItemClick(
            NavigationDrawerFragmentViewImpl.ToastMenuItemClickEvent event) {
        Toast.makeText(this, "Item " + event.getPosition() + " clicked.", Toast.LENGTH_SHORT)
                .show();
    }
}

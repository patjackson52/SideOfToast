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
import android.widget.Toast;

import com.squareup.otto.Subscribe;
import com.twotoasters.sideoftoast.Events;
import com.twotoasters.sideoftoast.SideOfToast;
import com.twotoasters.sideoftoast.items.ToastMenuItem;
import com.twotoasters.sideoftoast.mvp.BusProvider;

public class SitterCityActivity extends FragmentActivity {

    public static final int TITLE_TYPE = 0;
    public static final int ITEM_TYPE = 1;

    public static final int TITLE_ID = 0;
    public static final int HOME_ID = 1;
    public static final int NEED_ACCOUNT_ID = 2;
    public static final int LOGIN_ID = 3;
    public static final int SIGN_UP_ID = 4;
    public static final int ARE_YOU_SITTER_ID = 5;
    public static final int SWITCH_ID = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BusProvider.register(this);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new SCFragmentFragment())
                    .commit();
        }

        addUnauthParentMenu();
    }

    private void addUnauthParentMenu() {
        ToastMenuItem parentHomeTitleToastMenuItem =
                new ToastMenuItem.Builder(TITLE_ID, TITLE_TYPE)
                        .addText(R.id.navmenusection_label, R.string.parent_menu_title)
                        .setEnabled(false)
                        .build(this);

        ToastMenuItem homeToastMenuItem =
                new ToastMenuItem.Builder(HOME_ID, ITEM_TYPE)
                        .addText(R.id.navmenuitem_label, R.string.menu_title_home)
                        .addImage(R.id.navmenuitem_icon, R.drawable.selector_home_menu_icon)
                        .build(this);


        ToastMenuItem needAccountSectionHeader =
                new ToastMenuItem.Builder(NEED_ACCOUNT_ID, TITLE_TYPE)
                        .addText(R.id.navmenusection_label, R.string.menu_need_account_to_post_jobs)
                        .setEnabled(false)
                        .build(this);


        ToastMenuItem loginMenuItem =
                new ToastMenuItem.Builder(LOGIN_ID, ITEM_TYPE)
                        .addText(R.id.navmenuitem_label, R.string.menu_login_title)
                        .addImage(R.id.navmenuitem_icon, R.drawable.selector_login_menu_icon)
                        .build(this);

        ToastMenuItem signupMenuItem =
                new ToastMenuItem.Builder(SIGN_UP_ID, ITEM_TYPE)
                        .addText(R.id.navmenuitem_label, R.string.menu_sign_up_title)
                        .addImage(R.id.navmenuitem_icon, R.drawable.selector_join_menu_icon)
                        .build(this);

        ToastMenuItem areYouASitter =
                new ToastMenuItem.Builder(ARE_YOU_SITTER_ID, TITLE_TYPE)
                        .addText(R.id.navmenusection_label, R.string.menu_are_you_sitter_title)
                        .setEnabled(false)
                        .build(this);


        ToastMenuItem switchMenuItem =
                new ToastMenuItem.Builder(SWITCH_ID, ITEM_TYPE)
                        .addText(R.id.navmenuitem_label, R.string.menu_switch_title)
                        .addImage(R.id.navmenuitem_icon, R.drawable.selector_switch_menu_icon)
                        .build(this);

        new SideOfToast.Builder(R.layout.sittercity_navigation_drawer)
                .addItemViewType(0, R.layout.sittercity_section)
                .addItemViewType(1, R.layout.sittercity_item)
                .addMenuItem(parentHomeTitleToastMenuItem)
                .addMenuItem(homeToastMenuItem)
                .addMenuItem(needAccountSectionHeader)
                .addMenuItem(loginMenuItem)
                .addMenuItem(signupMenuItem)
                .addMenuItem(areYouASitter)
                .addMenuItem(switchMenuItem)
                .setSelected(1)
                .setWidth(300)
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
        getMenuInflater().inflate(R.menu.sittercity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.ebates) {
            startActivity(new Intent(this, EbatesActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public static class SCFragmentFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_sittercity, container, false);
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

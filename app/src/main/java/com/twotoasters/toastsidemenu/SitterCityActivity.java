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
import com.twotoasters.toastnavmenu.ToastMenuItem;
import com.twotoasters.toastnavmenu.mvp.NavigationDrawerFragmentViewImpl;

public class SitterCityActivity extends FragmentActivity {

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
                new ToastMenuItem.Builder(0, 0)
                        .addText(R.id.navmenusection_label, R.string.parent_menu_title)
                        .setEnabled(false)
                        .build(this);

        ToastMenuItem homeToastMenuItem =
                new ToastMenuItem.Builder(1, 1)
                        .addText(R.id.navmenuitem_label, R.string.menu_title_home)
                        .addImage(R.id.navmenuitem_icon, R.drawable.selector_home_menu_icon)
                        .build(this);


        ToastMenuItem needAccountSectionHeader =
                new ToastMenuItem.Builder(2, 0)
                        .addText(R.id.navmenusection_label, R.string.menu_need_account_to_post_jobs)
                        .setEnabled(false)
                        .build(this);


        ToastMenuItem loginMenuItem =
                new ToastMenuItem.Builder(3, 1)
                        .addText(R.id.navmenuitem_label, R.string.menu_login_title)
                        .addImage(R.id.navmenuitem_icon, R.drawable.selector_login_menu_icon)
                        .build(this);

        ToastMenuItem signupMenuItem =
                new ToastMenuItem.Builder(4, 1)
                        .addText(R.id.navmenuitem_label, R.string.menu_sign_up_title)
                        .addImage(R.id.navmenuitem_icon, R.drawable.selector_join_menu_icon)
                        .build(this);

        ToastMenuItem areYouASitter =
                new ToastMenuItem.Builder(5, 0)
                        .addText(R.id.navmenusection_label, R.string.menu_are_you_sitter_title)
                        .setEnabled(false)
                        .build(this);


        ToastMenuItem switchMenuItem =
                new ToastMenuItem.Builder(6, 1)
                        .addText(R.id.navmenuitem_label, R.string.menu_switch_title)
                        .addImage(R.id.navmenuitem_icon, R.drawable.selector_switch_menu_icon)
                        .build(this);

        new SideOfToast.Builder(R.layout.navigation_drawer)
                .addItemViewType(0, R.layout.navmenu_section)
                .addItemViewType(1, R.layout.navmenu_item)
                .addMenuItem(homeToastMenuItem)
                .addMenuItem(parentHomeTitleToastMenuItem)
                .addMenuItem(needAccountSectionHeader)
                .addMenuItem(loginMenuItem)
                .addMenuItem(signupMenuItem)
                .addMenuItem(areYouASitter)
                .addMenuItem(switchMenuItem)
                .setWidth(800)
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


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class SCFragmentFragment extends Fragment {

        public SCFragmentFragment() {
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

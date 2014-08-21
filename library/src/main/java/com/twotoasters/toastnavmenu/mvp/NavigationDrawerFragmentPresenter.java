package com.twotoasters.toastnavmenu.mvp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.squareup.otto.Subscribe;
import com.twotoasters.toastnavmenu.SideOfToast;

/**
 * Created by patrickjackson on 4/23/14.
 */
public class NavigationDrawerFragmentPresenter {

    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

    NavigationDrawerFragmentModel model;
    NavigationDrawerFragmentView view;

    public NavigationDrawerFragmentPresenter(NavigationDrawerFragmentModel model,
                                             NavigationDrawerFragmentView view) {
        this.model = model;
        this.view = view;
        refreshNavMenu();
    }


    public Bundle onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_SELECTED_POSITION, model.getCurrentSelectedPosition());
        return outState;
    }

    public void restoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
//            model.setCurrentSelectedPosition(savedInstanceState.getInt(STATE_SELECTED_POSITION));
//            view.setCurrentSelectedPosition(model.getCurrentSelectedPosition());

        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        view.setDrawerToggleConfiguration(newConfig);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return view.drawerToggleOnOptionsItemSelected(item);
    }

    public void onPrepareOptionsMenu(Menu menu) {

    }

    public void refreshNavMenu() {
        view.setMenuItems(model.getMenuItems(),
                model.getSideOfToast());
        if (model.getFooterItem() != null) {
            view.setFooterLayout(model.getFooterItem());
        }
        view.setCurrentSelectedPosition(model.getCurrentSelectedPosition());
    }

    @Subscribe
    public void onSetSelectedItem(SideOfToast.SetSelectedItemEvent event) {
        model.setCurrentSelectedPosition(event.getPosition());
        view.setCurrentSelectedPosition(event.getPosition());
    }

    @Subscribe
    public void onSetResource(SetResourceEvent event) {
        if (event.str != null) {
            model.updateSideOfToast(event.menuId, event.layoutId, event.str);
        } else {
            model.updateSideOfToast(event.menuId, event.layoutId, event.resourceId);
        }
        refreshNavMenu();
    }

    public static class SetResourceEvent {
        private int menuId, layoutId, resourceId;
        private String str;

        public SetResourceEvent(int menuId, int layoutId, int resourceId) {
            this.layoutId = layoutId;
            this.resourceId = resourceId;
            this.menuId = menuId;
        }

        public SetResourceEvent(int menuId, int layoutId, String str) {
            this.menuId = menuId;
            this.layoutId = layoutId;
            this.str = str;
        }
    }

}

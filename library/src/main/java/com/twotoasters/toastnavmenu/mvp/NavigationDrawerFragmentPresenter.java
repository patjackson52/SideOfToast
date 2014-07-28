package com.twotoasters.toastnavmenu.mvp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.sittercity.R;
import com.sittercity.SitterCityApp;
import com.sittercity.activity.BaseActivity;
import com.sittercity.activity.BaseActivity.LoginEvent;
import com.sittercity.activity.BaseActivity.LogoutEvent;
import com.sittercity.gateway.ApiCredentialsGateway;
import com.sittercity.gateway.ApiCredentialsGatewayImpl;
import com.sittercity.gateway.UserDataGateway;
import com.sittercity.gateway.UserDataGateway.RoleTypes;
import com.sittercity.model.NavMenuItem;
import com.sittercity.mvp.NavigationDrawerFragmentView.HomeTappedEvent;
import com.sittercity.mvp.NavigationDrawerFragmentView.JobApplicationsTappedEvent;
import com.sittercity.mvp.NavigationDrawerFragmentView.NavMenuItemSelectedEvent;
import com.sittercity.mvp.NavigationDrawerFragmentView.SwitchRolesTappedEvent;
import com.sittercity.util.AccountDataUtils;
import com.sittercity.util.BusProvider;
import com.squareup.otto.Subscribe;

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
            model.setCurrentSelectedPosition(savedInstanceState.getInt(STATE_SELECTED_POSITION));
            view.setCurrentSelectedPosition(model.getCurrentSelectedPosition());

        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        view.setDrawerToggleConfiguration(newConfig);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return view.drawerToggleOnOptionsItemSelected(item);
    }

    public void onPrepareOptionsMenu(Menu menu) {
        if (!view.isDrawerOpen()) {
        } else {
            for (int i = 0; i < menu.size(); i++) {
                if (menu.getItem(i).getItemId() != R.id.action_search) {
                    menu.getItem(i).setVisible(false);
                }
            }
        }
    }

    public void refreshNavMenu() {
//        view.setMenuItems(model.getNavMenuItemsForRole(userDataGateway.getRole(),
//                apiCredentialsGateway.isAuthenticated()
//        ));
//        model.setCurrentSelectedPosition(apiCredentialsGateway.isAuthenticated() ? 0 : 1);
//        view.setCurrentSelectedPosition(model.getCurrentSelectedPosition());
    }

    @Subscribe
    public void onNavMenuItemSelected(NavMenuItemSelectedEvent event) {
        int selectedId = model.getMenuIdForPosition(event.getPosition());
        if (selectedId != NavMenuItem.POST_JOB_ID) {
            model.setCurrentSelectedPosition(event.getPosition());
            view.setCurrentSelectedPosition(event.getPosition());
        } else {
            // Workaround for singleChoice mode
            view.setCurrentSelectedPosition(model.getCurrentSelectedPosition());
        }

        switch (selectedId) {
            default:
                break;
        }
    }

}

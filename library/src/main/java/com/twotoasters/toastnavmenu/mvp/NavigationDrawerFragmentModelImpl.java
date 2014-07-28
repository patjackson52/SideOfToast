package com.twotoasters.toastnavmenu.mvp;

import com.sittercity.R;
import com.sittercity.gateway.UserDataGateway.RoleTypes;
import com.sittercity.model.NavMenuItem;

import static com.sittercity.model.NavMenuItem.APPLICATIONS_ID;
import static com.sittercity.model.NavMenuItem.ARE_YOU_A_PARENT_SECTION_ID;
import static com.sittercity.model.NavMenuItem.ARE_YOU_A_SITTER_SECTION_ID;
import static com.sittercity.model.NavMenuItem.HOME_ID;
import static com.sittercity.model.NavMenuItem.JOB_APPLICATIONS_ID;
import static com.sittercity.model.NavMenuItem.JOIN_US_ID;
import static com.sittercity.model.NavMenuItem.LOGGED_OUT_SECTION_ID;
import static com.sittercity.model.NavMenuItem.LOG_IN_ID;
import static com.sittercity.model.NavMenuItem.MESSAGES_ID;
import static com.sittercity.model.NavMenuItem.POST_JOB_ID;
import static com.sittercity.model.NavMenuItem.PROFILE_ID;
import static com.sittercity.model.NavMenuItem.SWITCH_ROLES_ID;

public class NavigationDrawerFragmentModelImpl implements NavigationDrawerFragmentModel {

    private int currentSelectedPosition = 1;

    private NavMenuItem[] navMenuItems;

    public NavigationDrawerFragmentModelImpl(int startPosition) {
        currentSelectedPosition = startPosition;

    }

    @Override
    public NavMenuItem[] getNavMenuItemsForRole(int roleType, boolean loggedIn) {
        switch (roleType) {
            case RoleTypes.PARENT:
                navMenuItems = loggedIn ? getAuthParentMenuItems() : getUnauthParentMenuItems();
                return navMenuItems;
            case RoleTypes.SITTER:
                navMenuItems = loggedIn ? getAuthSitterMenuItems() : getUnauthSitterMenuItems();
                return navMenuItems;
            default:
                break;
        }
        return null;
    }

    private NavMenuItem[] getUnauthParentMenuItems() {
        return new NavMenuItem[]{
                NavMenuItem.createHeader(NavMenuItem.PARENT_VIEW_SECTION_ID, R.string.navmenu_parentview_section),
                NavMenuItem.createItem(HOME_ID, R.string.navmenu_home, R.drawable.selector_home_menu_icon),
                NavMenuItem.createHeader(LOGGED_OUT_SECTION_ID, R.string.navmenu_logged_out_section_parent),
                NavMenuItem.createItem(LOG_IN_ID, R.string.navmenu_login, R.drawable.selector_login_menu_icon),
                NavMenuItem.createItem(JOIN_US_ID, R.string.navmenu_join_us, R.drawable.selector_join_menu_icon),
                NavMenuItem.createHeader(ARE_YOU_A_SITTER_SECTION_ID, R.string.navmenu_are_you_sitter),
                NavMenuItem.createItem(SWITCH_ROLES_ID,
                        R.string.navmenu_switch_to_sitters,
                        R.drawable.selector_switch_menu_icon)
        };
    }

    private NavMenuItem[] getAuthParentMenuItems() {
        return new NavMenuItem[]{
                NavMenuItem.createItem(HOME_ID,
                        R.string.navmenu_home,
                        R.drawable.selector_home_menu_icon),
                NavMenuItem.createItem(MESSAGES_ID,
                        R.string.navmenu_messages,
                        R.drawable.selector_messages_menu_icon),
                NavMenuItem.createItem(JOB_APPLICATIONS_ID,
                        R.string.navmenu_job_applications,
                        R.drawable.selector_applications_menu_icon),
                NavMenuItem.createItem(POST_JOB_ID,
                        R.string.navmenu_post_a_job,
                        R.drawable.selector_post_job_menu_icon)
        };
    }

    private NavMenuItem[] getUnauthSitterMenuItems() {
        return new NavMenuItem[]{
                NavMenuItem.createHeader(NavMenuItem.SITTER_VIEW_SECTION_ID, R.string.navmenu_sitterview_section),
                NavMenuItem.createItem(HOME_ID, R.string.navmenu_home, R.drawable.selector_home_menu_icon),
                NavMenuItem.createHeader(LOGGED_OUT_SECTION_ID,
                        R.string.navmenu_logged_out_section_sitter),
                NavMenuItem.createItem(LOG_IN_ID, R.string.navmenu_login, R.drawable.selector_login_menu_icon),
                NavMenuItem.createItem(JOIN_US_ID, R.string.navmenu_join_us, R.drawable.selector_join_menu_icon),
                NavMenuItem.createHeader(ARE_YOU_A_PARENT_SECTION_ID, R.string.navmenu_are_you_parent),
                NavMenuItem.createItem(SWITCH_ROLES_ID,
                        R.string.navmenu_switch_to_parents, R.drawable.selector_switch_menu_icon)
        };
    }

    private NavMenuItem[] getAuthSitterMenuItems() {
        return new NavMenuItem[]{
                NavMenuItem.createItem(HOME_ID, R.string.navmenu_home, R.drawable.selector_home_menu_icon),
                NavMenuItem.createItem(MESSAGES_ID, R.string.navmenu_messages,
                        R.drawable.selector_messages_menu_icon),
                NavMenuItem.createItem(APPLICATIONS_ID, R.string.navmenu_applications,
                        R.drawable.selector_applications_menu_icon),
                NavMenuItem.createItem(PROFILE_ID, R.string.navmenu_profile, R.drawable.selector_profile_menu_icon)
        };
    }

    @Override
    public int getCurrentSelectedPosition() {
        return currentSelectedPosition;
    }

    @Override
    public void setCurrentSelectedPosition(int position) {
        this.currentSelectedPosition = position;
    }

    @Override
    public int getSelectedMenuId() {
        return getMenuIdForPosition(currentSelectedPosition);
    }

    @Override
    public int getMenuIdForPosition(int position) {
        return navMenuItems[position].getId();
    }
}

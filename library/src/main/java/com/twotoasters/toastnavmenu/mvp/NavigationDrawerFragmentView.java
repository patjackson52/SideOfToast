package com.twotoasters.toastnavmenu.mvp;

import android.content.res.Configuration;
import android.view.MenuItem;

import com.twotoasters.toastnavmenu.ToastMenuFooterItem;
import com.twotoasters.toastnavmenu.ToastMenuItem;
import com.twotoasters.toastnavmenu.SideOfToast;


/**
 * Created by patrickjackson on 4/23/14.
 */
public interface NavigationDrawerFragmentView {

//    void setMenuItems(NavMenuItem[] items);

    void setFooterLayout(ToastMenuFooterItem footerItem);

    void setMenuItems(ToastMenuItem[] items,
                      SideOfToast sideOfToast);

    void setCurrentSelectedPosition(int position);

    void setDrawerToggleConfiguration(Configuration newConfig);

    boolean drawerToggleOnOptionsItemSelected(MenuItem item);

    boolean isDrawerOpen();

    public static class NavMenuItemSelectedEvent {
        private int position;

        public NavMenuItemSelectedEvent(int position) {
            this.position = position;
        }

//        public int getPosition() {
//            return position;
//        }
//
//        public void setPosition(int position) {
//            this.position = position;
//        }
    }
}

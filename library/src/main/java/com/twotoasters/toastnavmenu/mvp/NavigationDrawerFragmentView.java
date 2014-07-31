package com.twotoasters.toastnavmenu.mvp;

import android.content.res.Configuration;
import android.view.MenuItem;

import com.twotoasters.toastnavmenu.Toaster;


/**
 * Created by patrickjackson on 4/23/14.
 */
public interface NavigationDrawerFragmentView {

//    void setMenuItems(NavMenuItem[] items);

    void setMenuItems(com.twotoasters.toastnavmenu.MenuItem[] items,
                      Toaster toaster);

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

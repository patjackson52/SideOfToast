package com.twotoasters.sideoftoast;


/**
 * Created by patrickjackson on 4/23/14.
 */
public interface NavigationDrawerFragmentModel {


    int getCurrentSelectedPosition();

    void setCurrentSelectedPosition(int position);

    int getSelectedMenuId();

    int getMenuIdForPosition(int position);

    ToastMenuItem[] getMenuItems();

    SideOfToast getSideOfToast();

    ToastMenuFooterItem getFooterItem();
}

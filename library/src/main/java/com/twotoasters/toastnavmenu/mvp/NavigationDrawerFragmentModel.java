package com.twotoasters.toastnavmenu.mvp;


import com.twotoasters.toastnavmenu.ToastMenuFooterItem;
import com.twotoasters.toastnavmenu.ToastMenuItem;
import com.twotoasters.toastnavmenu.SideOfToast;

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

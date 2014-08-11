package com.twotoasters.toastnavmenu.mvp;

import com.twotoasters.toastnavmenu.ToastMenuFooterItem;
import com.twotoasters.toastnavmenu.ToastMenuItem;
import com.twotoasters.toastnavmenu.SideOfToast;

public class NavigationDrawerFragmentModelImpl implements NavigationDrawerFragmentModel {

    private int currentSelectedPosition = 1;
    private SideOfToast sideOfToast;


    public NavigationDrawerFragmentModelImpl(SideOfToast sideOfToast,
                                             int startPosition) {
        currentSelectedPosition = startPosition;
        this.sideOfToast = sideOfToast;

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
        return position;
    }

    @Override
    public ToastMenuItem[] getMenuItems() {
        return sideOfToast.getItems();
    }

    @Override
    public SideOfToast getSideOfToast() {
        return sideOfToast;
    }

    @Override
    public ToastMenuFooterItem getFooterItem() {
        return sideOfToast.getFooterItem();
    }
}

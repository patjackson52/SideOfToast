package com.twotoasters.toastnavmenu.mvp;

import com.twotoasters.toastnavmenu.ToastMenuFooterItem;
import com.twotoasters.toastnavmenu.ToastMenuItem;
import com.twotoasters.toastnavmenu.SideOfToast;
import com.twotoasters.toastnavmenu.Utils;

public class NavigationDrawerFragmentModelImpl implements NavigationDrawerFragmentModel {

    private int currentSelectedPosition = 1;
    private SideOfToast sideOfToast;


    public NavigationDrawerFragmentModelImpl(SideOfToast sideOfToast,
                                             int startPosition) {
        currentSelectedPosition = startPosition;
        this.sideOfToast = sideOfToast;
        currentSelectedPosition = sideOfToast.getSelectedPosition();

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
        return Utils.getArrayOfMenuItems(sideOfToast);
    }

    @Override
    public SideOfToast getSideOfToast() {
        return sideOfToast;
    }

    @Override
    public ToastMenuFooterItem getFooterItem() {
        return sideOfToast.getFooterItem();
    }

    @Override
    public void updateSideOfToast(int menuId, int layoutId, String str) {
       sideOfToast.updateResource(menuId, layoutId, str);
    }

    @Override
    public void updateSideOfToast(int menuId, int layoutId, int resourceId) {
        sideOfToast.updateImageResource(menuId, layoutId, resourceId);

    }

}

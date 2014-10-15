package com.twotoasters.sideoftoast.mvp;

import com.twotoasters.sideoftoast.SideOfToast;
import com.twotoasters.sideoftoast.items.ToastMenuFooterItem;
import com.twotoasters.sideoftoast.items.ToastMenuItem;
import com.twotoasters.sideoftoast.items.Utils;

public class SideNavModel {

    private int currentSelectedPosition = 1;
    private SideOfToast sideOfToast;


    public SideNavModel(SideOfToast sideOfToast,
                        int startPosition) {
        currentSelectedPosition = startPosition;
        this.sideOfToast = sideOfToast;
        currentSelectedPosition = sideOfToast.getSelectedPosition();
    }

    public int getCurrentSelectedPosition() {
        return currentSelectedPosition;
    }

    public void setCurrentSelectedPosition(int position) {
        this.currentSelectedPosition = position;
    }

    public int getSelectedMenuId() {
        return getMenuIdForPosition(currentSelectedPosition);
    }

    public int getMenuIdForPosition(int position) {
        return position;
    }

    public ToastMenuItem[] getMenuItems() {
        return Utils.getArrayOfMenuItems(sideOfToast);
    }

    public SideOfToast getSideOfToast() {
        return sideOfToast;
    }

    public ToastMenuFooterItem getFooterItem() {
        return sideOfToast.getFooterItem();
    }

    public void updateStringResource(int menuId, int layoutId, String str) {
       sideOfToast.updateStringResource(menuId, layoutId, str);
    }

    public void updateImageResource(int menuId, int layoutId, int resourceId) {
        sideOfToast.updateImageResource(menuId, layoutId, resourceId);
    }

}

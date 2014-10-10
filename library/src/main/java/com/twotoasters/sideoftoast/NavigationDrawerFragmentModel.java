package com.twotoasters.sideoftoast;

public class NavigationDrawerFragmentModel {

    private int currentSelectedPosition = 1;
    private SideOfToast sideOfToast;


    public NavigationDrawerFragmentModel(SideOfToast sideOfToast,
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

    public void updateSideOfToast(int menuId, int layoutId, String str) {
       sideOfToast.updateResource(menuId, layoutId, str);
    }

    public void updateSideOfToast(int menuId, int layoutId, int resourceId) {
        sideOfToast.updateImageResource(menuId, layoutId, resourceId);

    }

}

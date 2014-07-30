package com.twotoasters.toastnavmenu.mvp;

public class NavigationDrawerFragmentModelImpl implements NavigationDrawerFragmentModel {

    private int currentSelectedPosition = 1;


    public NavigationDrawerFragmentModelImpl(int startPosition) {
        currentSelectedPosition = startPosition;

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
}

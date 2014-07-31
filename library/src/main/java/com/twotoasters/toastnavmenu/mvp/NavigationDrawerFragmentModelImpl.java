package com.twotoasters.toastnavmenu.mvp;

import com.twotoasters.toastnavmenu.MenuItem;
import com.twotoasters.toastnavmenu.Toaster;

public class NavigationDrawerFragmentModelImpl implements NavigationDrawerFragmentModel {

    private int currentSelectedPosition = 1;
    private Toaster toaster;


    public NavigationDrawerFragmentModelImpl(Toaster toaster,
                                             int startPosition) {
        currentSelectedPosition = startPosition;
        this.toaster = toaster;

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
    public MenuItem[] getMenuItems() {
        return toaster.getItems();
    }

    @Override
    public Toaster getToaster() {
        return toaster;
    }
}

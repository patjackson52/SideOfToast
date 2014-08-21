package com.twotoasters.toastnavmenu;

/**
 * Created by patrickjackson on 8/20/14.
 */
public final class Utils {
    private Utils() {
    }


    public static ToastMenuItem[] getArrayOfMenuItems(SideOfToast sideOfToast) {
        ToastMenuItem[] items = new ToastMenuItem[sideOfToast.getItems().size()];
        sideOfToast.getItems().values().toArray(items);
        return items;
    }
}

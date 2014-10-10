package com.twotoasters.sideoftoast;

public final class Utils {
    private Utils() {
    }


    public static ToastMenuItem[] getArrayOfMenuItems(SideOfToast sideOfToast) {
        ToastMenuItem[] items = new ToastMenuItem[sideOfToast.getItems().size()];
        sideOfToast.getItems().values().toArray(items);
        return items;
    }
}

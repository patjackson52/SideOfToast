package com.twotoasters.sideoftoastsample;

import android.app.Activity;

import com.twotoasters.sideoftoast.SideOfToast;
import com.twotoasters.sideoftoast.items.ToastMenuItem;

public class SideNavMenu {

    private static final int TYPES_OF_BREAD_ID = 0;
    private static final int PLAIN_TOAST_MENU_ID = 1;
    private static final int BAGEL_MENU_ID = 2;
    private static final int OTHER_TOAST_MENU_ID = 3;
    private static final int TOTALS_HEADER_ID = 4;
    private static final int TOTALS_ID = 5;

    private static final int TOAST_MENU_TYPE = 1;
    private static final int TOAST_MENU_HEADER_TYPE = 2;
    private static final int TOAST_MENU_FOOTER_TYPE = 3;

    public static void addBreadMenu(Activity activity) {
        ToastMenuItem breadHeader =
                new ToastMenuItem.Builder(TYPES_OF_BREAD_ID, TOAST_MENU_HEADER_TYPE)
                        .addText(R.id.sidenav_header_label, "Types of Bread")
                        .setEnabled(false)
                        .build(activity);
        ToastMenuItem whiteItem =
                new ToastMenuItem.Builder(PLAIN_TOAST_MENU_ID, TOAST_MENU_TYPE)
                        .addText(R.id.sidenav_label, "White Bread")
                        .addImage(R.id.sidenav_icon, R.drawable.light_untoasted)
                        .build(activity);
        ToastMenuItem wheatItem =
                new ToastMenuItem.Builder(OTHER_TOAST_MENU_ID, TOAST_MENU_TYPE)
                        .addText(R.id.sidenav_label, "Wheat Bread")
                        .addImage(R.id.sidenav_icon, R.drawable.dark_untoasted)
                        .build(activity);
        ToastMenuItem bagelItem =
                new ToastMenuItem.Builder(BAGEL_MENU_ID, TOAST_MENU_TYPE)
                        .addText(R.id.sidenav_label, "Bagel")
                        .addImage(R.id.sidenav_icon, R.drawable.bagel_untoasted)
                        .build(activity);
        ToastMenuItem totalsHeader =
                new ToastMenuItem.Builder(TOTALS_HEADER_ID, TOAST_MENU_HEADER_TYPE)
                        .addText(R.id.sidenav_header_label, "Total Order")
                        .setEnabled(false)
                        .build(activity);
        ToastMenuItem footer =
                new ToastMenuItem.Builder(TOTALS_ID, TOAST_MENU_FOOTER_TYPE)
                        .addText(R.id.txt_num_white_ordered, "0")
                        .addText(R.id.txt_num_wheat_ordered, "0")
                        .addText(R.id.txt_num_bagels_ordered, "0")
                        .addText(R.id.txt_total_ordered, "0")
                        .setEnabled(false)
                        .build(activity);
        new SideOfToast.Builder()
                .addItemViewType(TOAST_MENU_TYPE, R.layout.bread_item)
                .addItemViewType(TOAST_MENU_HEADER_TYPE, R.layout.sidenav_header)
                .addItemViewType(TOAST_MENU_FOOTER_TYPE, R.layout.sidenav_footer)
                .addMenuItem(breadHeader)
                .addMenuItem(whiteItem)
                .addMenuItem(wheatItem)
                .addMenuItem(bagelItem)
                .addMenuItem(totalsHeader)
                .addMenuItem(footer)
                .setSlidingContent(true)
                .setWidth(300)
                .setSelected(1)
                .build()
                .create(activity);
    }
}

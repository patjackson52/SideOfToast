package com.twotoasters.sideoftoastsample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.twotoasters.sideoftoast.SideOfToast;
import com.twotoasters.sideoftoast.items.ToastMenuItem;

public class BreadActivity extends FragmentActivity {

    private static final int TYPES_OF_BREAD_ID = 0;
    private static final int PLAIN_TOAST_MENU_ID = 1;
    private static final int BAGEL_MENU_ID = 2;
    private static final int OTHER_TOAST_MENU_ID = 3;
    private static final int TOTALS_HEADER_ID = 4;
    private static final int TOTALS_ID = 5;

    private static final int TOAST_MENU_TYPE = 1;
    private static final int TOAST_MENU_HEADER_TYPE = 2;
    private static final int TOAST_MENU_FOOTER_TYPE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addBreadMenu();
    }

    private void addBreadMenu() {
        ToastMenuItem breadHeader =
                new ToastMenuItem.Builder(TYPES_OF_BREAD_ID, TOAST_MENU_HEADER_TYPE)
                        .addText(R.id.sidenav_header_label, "Types of Bread")
                        .setEnabled(false)
                        .build(this);
        ToastMenuItem whiteItem =
                new ToastMenuItem.Builder(PLAIN_TOAST_MENU_ID, TOAST_MENU_TYPE)
                        .addText(R.id.sidenav_label, "White Bread")
                        .addImage(R.id.sidenav_icon, R.drawable.light_untoasted)
                        .build(this);
        ToastMenuItem wheatItem =
                new ToastMenuItem.Builder(OTHER_TOAST_MENU_ID, TOAST_MENU_TYPE)
                        .addText(R.id.sidenav_label, "Wheat Bread")
                        .addImage(R.id.sidenav_icon, R.drawable.dark_untoasted)
                        .build(this);
        ToastMenuItem bagelItem =
                new ToastMenuItem.Builder(BAGEL_MENU_ID, TOAST_MENU_TYPE)
                        .addText(R.id.sidenav_label, "Bagel")
                        .addImage(R.id.sidenav_icon, R.drawable.bagel_untoasted)
                        .build(this);
        ToastMenuItem totalsHeader =
                new ToastMenuItem.Builder(TOTALS_HEADER_ID, TOAST_MENU_HEADER_TYPE)
                        .addText(R.id.sidenav_header_label, "Total Order")
                        .setEnabled(false)
                        .build(this);
        ToastMenuItem footer =
                new ToastMenuItem.Builder(TOTALS_ID, TOAST_MENU_FOOTER_TYPE)
                        .addText(R.id.txtSidebarName, R.string.ebates_sidebar_account_name)
                        .addText(R.id.txtSidebarCashPaidValue, "0")
                        .addText(R.id.txtSidebarCashPendingValue, "0")
                        .addText(R.id.txtSidebarTotalCashValue, "0")
                        .addText(R.id.txtSidebarNextCashValue, "0")
                        .setEnabled(false)
                        .build(this);
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
                .setWidth(300)
                .setSelected(1)
                .includeActionBarInDrawerContent(true)
                .build()
                .create(this);
    }
}

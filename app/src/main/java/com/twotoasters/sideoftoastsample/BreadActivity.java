package com.twotoasters.sideoftoastsample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.twotoasters.sideoftoast.SideOfToast;
import com.twotoasters.sideoftoast.items.ToastMenuItem;

public class BreadActivity extends FragmentActivity {

    private static final int PLAIN_TOAST_MENU_ID = 0;
    private static final int BAGEL_MENU_ID = 1;
    private static final int OTHER_TOAST_MENU_ID = 2;

    private static final int TOAST_MENU_TYPE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addBreadMenu();
    }

    private void addBreadMenu() {
        ToastMenuItem featuredItem =
                new ToastMenuItem.Builder(PLAIN_TOAST_MENU_ID, TOAST_MENU_TYPE)
                        .addText(R.id.txtSidebar, "Plain Toast")
                        .addImage(R.id.navmenuitem_icon, R.drawable.light_untoasted)
                        .build(this);
        ToastMenuItem allStores =
                new ToastMenuItem.Builder(BAGEL_MENU_ID, TOAST_MENU_TYPE)
                        .addText(R.id.txtSidebar, "Bagel")
                        .addImage(R.id.navmenuitem_icon, R.drawable.bagel_untoasted)
                        .build(this);
        ToastMenuItem tafItem =
                new ToastMenuItem.Builder(OTHER_TOAST_MENU_ID, TOAST_MENU_TYPE)
                        .addText(R.id.txtSidebar, "Dark Toast")
                        .addImage(R.id.navmenuitem_icon, R.drawable.dark_untoasted)
                        .build(this);

        new SideOfToast.Builder()
                .addItemViewType(TOAST_MENU_TYPE, R.layout.ebates_item_sidebar)
                .addMenuItem(featuredItem)
                .addMenuItem(allStores)
                .addMenuItem(tafItem)
                .setWidth(300)
                .setSelected(0)
                .includeActionBarInDrawerContent(true)
                .build()
                .create(this);
    }
}

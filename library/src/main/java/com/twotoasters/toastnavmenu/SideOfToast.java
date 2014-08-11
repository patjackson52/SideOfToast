package com.twotoasters.toastnavmenu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by patrickjackson on 7/27/14.
 */
public class SideOfToast implements Serializable {


    public int getFooterLayout() {
        return footer.getLayoutId();
    }


    public interface ReadyForToast {
        void ToastMenuItemClicked(int position);
    }

    private static final String TAG = "SideOfToast";
    private static final int DEFAULT_WIDTH = 700;

    private final int listLayoutId;
    private final HashMap itemViewTypes;
    private ToastMenuItem items[];
    private ToastMenuFooterItem footer;
    private int width;
    private static Level logLevel = Level.OFF;
    DrawerLayout drawerLayout;

    private SideOfToast(Builder builder) {
        items = new ToastMenuItem[builder.items.size()];
        builder.items.toArray(items);

        listLayoutId = builder.listLayoutId;
        this.itemViewTypes = builder.itemViewTypes;
        this.width = builder.width;
        this.footer = builder.footerItem;

        logLevel = (builder.logLevel != null)
                ? builder.logLevel
                : Level.OFF;
    }

    public static void log(String message) {
        Logger.getLogger(TAG).log(logLevel, message);
    }

    public View create(FragmentActivity activity) {
        final ViewGroup firstViewInLayout = (ViewGroup) ((ViewGroup) activity
                .findViewById(android.R.id.content)).getChildAt(0);
        final ViewGroup activityRoot = (ViewGroup) ((ViewGroup) activity
                .findViewById(android.R.id.content));

        drawerLayout = new DrawerLayout(activity);

        width = (width == 0) ? DEFAULT_WIDTH : width;

        DrawerLayout.LayoutParams lp = new DrawerLayout.LayoutParams(
                width,
                LinearLayout.LayoutParams.MATCH_PARENT);
        log(activity.getString(R.string.log_setting_width) + width);

        lp.gravity = Gravity.START;


        final FrameLayout fl = new FrameLayout(activity);
        fl.setId(R.id.drawer_contents);
        fl.setLayoutParams(lp);

        activityRoot.removeView(firstViewInLayout);
        drawerLayout.addView(firstViewInLayout,
                new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT)
        );
        drawerLayout.addView(fl);
        drawerLayout.setId(R.id.drawer_layout);

        activityRoot.addView(drawerLayout);


        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        Fragment fragment = (Fragment) NavigationDrawerFragment.getInstance(this);
        fragmentTransaction.add(R.id.drawer_contents,
                fragment,
                "NavMenu");
        fragmentTransaction.commit();

        log(activity.getString(R.string.log_create_finished));
        return drawerLayout;
    }


    private View inflateListView(FragmentActivity activity) {
        return activity.getLayoutInflater().inflate(listLayoutId, null);
    }

    public int getListLayoutId() {
        return listLayoutId;
    }

    public HashMap getItemViewTypes() {
        return itemViewTypes;
    }

    public ToastMenuItem[] getItems() {
        return items;
    }

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    public int getLayoutForType(int type) {
        return (int) getItemViewTypes().get(type);
    }

    public ToastMenuFooterItem getFooterItem() {
        return footer;
    }

    public static class Builder {
        private final int listLayoutId;
        private HashMap itemViewTypes;
        private ArrayList<ToastMenuItem> items;
        private ToastMenuFooterItem footerItem;
        private Level logLevel;
        public int width;


        public Builder(int listLayoutId) {
            this.listLayoutId = listLayoutId;
            items = new ArrayList<>();
            itemViewTypes = new HashMap();
        }

        public Builder addMenuItem(ToastMenuItem item) {
            items.add(item);
            return this;
        }

        public Builder addFooter(ToastMenuFooterItem footerItem) {
            this.footerItem = footerItem;
            return this;
        }

        public Builder addItemViewType(final int type, final int layoutResourceId) {
            this.itemViewTypes.put(type, layoutResourceId);
            return this;
        }

        /**
         * Logging level from Log class constants
         * Log.VERBOSE, Log.ERROR, etc
         *
         * @param level
         * @return
         */
        public Builder setLogLevel(Level level) {
            this.logLevel = level;
            return this;
        }

        /**
         * Sets the width of the NavigationDrawer when open.
         * Default is 700dp
         *
         * @param width
         * @return
         */
        public Builder setWidth(int width) {
            this.width = width;
            return this;
        }


        public SideOfToast build() {
            if (itemViewTypes.size() < 1) {
//                throw new NoItemViewTypesException();
            }
            Collections.sort(items);
            return new SideOfToast(this);
        }

//
//        private void countItemViewTypes() {
//            ArrayList<Integer> list = new ArrayList<>();
//            for (ToastMenuItem item: items) {
//                if (list.contains(item.))
//            }
//
//        }


        public static class NoItemViewTypesException extends Exception {
        }
    }

}

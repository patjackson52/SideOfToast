package com.twotoasters.sideoftoast;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.twotoasters.sideoftoast.items.ToastMenuFooterItem;
import com.twotoasters.sideoftoast.items.ToastMenuItem;
import com.twotoasters.sideoftoast.mvp.BusProvider;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Holds data for the Side Nav and creates the Navigation Drawer
 * when when create() is called.
 */
public class SideOfToast implements Serializable {


    public int getListLayoutId() {
        return listLayoutId;
    }

    public interface ReadyForToast {
        void ToastMenuItemClicked(int position);
    }

    private static final String TAG = "SideOfToast";
    private static final int DEFAULT_WIDTH = 300;

    private final int listLayoutId;
    private final HashMap itemViewTypes;
    private LinkedHashMap<Integer, ToastMenuItem> items;
    private ToastMenuFooterItem footer;
    private int width;
    private int selectedPosition;
    private static Level logLevel = Level.OFF;

    private SideOfToast(Builder builder) {
        items = builder.items;

        listLayoutId = builder.listLayoutId;
        this.itemViewTypes = builder.itemViewTypes;
        this.width = builder.width;
        this.footer = builder.footerItem;

        logLevel = (builder.logLevel != null)
                ? builder.logLevel
                : Level.OFF;
        this.selectedPosition = builder.selectedPosition;
    }

    public static void log(String message) {
        Logger.getLogger(TAG).log(logLevel, message);
    }

    /**
     * Inserts a NavigationDrawer View in the view hierarchy for the supplied activity.
     * Then attaches a fragment containing the Nav Menu
     * @param activity
     * @return SideOfToast
     */
    public SideOfToast create(FragmentActivity activity) {
        final ViewGroup firstViewInLayout = (ViewGroup) ((ViewGroup) activity
                .findViewById(android.R.id.content)).getChildAt(0);
        final ViewGroup activityRoot = (ViewGroup) ((ViewGroup) activity
                .findViewById(android.R.id.content));

        DrawerLayout drawerLayout = new DrawerLayout(activity);

        width = (width == 0) ? DEFAULT_WIDTH : width;
        width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                width, activity.getResources().getDisplayMetrics());

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
        Fragment fragment = (Fragment) SideNavFragment.getInstance(this);
        fragmentTransaction.add(R.id.drawer_contents,
                fragment,
                SideNavFragment.TAG);
        fragmentTransaction.commit();

        log(activity.getString(R.string.log_create_finished));
        return this;
    }


    public void setSelected(int position) {
        BusProvider.post(new SetSelectedItemEvent(position));
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public HashMap getItemViewTypes() {
        return itemViewTypes;
    }

    public HashMap<Integer, ToastMenuItem> getItems() {
        return items;
    }

    public int getLayoutForType(int type) {
        return (int) getItemViewTypes().get(type);
    }

    public ToastMenuFooterItem getFooterItem() {
        return footer;
    }

    public int getFooterLayout() {
        return footer.getLayoutId();
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }


    public void updateResource(int id, int layoutId, String str) {
        if (footer.getMenuId() == id) {
            footer.updateTextMap(layoutId, str);
        } else {
            ToastMenuItem tmp = items.get(Integer.valueOf(id));
            if (tmp != null) {
                tmp.updateTextMap(layoutId, str);
            }
        }

    }

    public void updateImageResource(int menuId, int layoutId, int resourceId) {
        if (footer.getMenuId() == menuId) {
            footer.updateImageMap(layoutId, resourceId);
        } else {
            ToastMenuItem tmp = items.get(Integer.valueOf(menuId));
            if (tmp != null) {
                tmp.updateImageMap(layoutId, resourceId);
            }
        }
    }

    private void updateTextResource(int layoutId, int resourceId) {

    }

    private void updateImageResource(int layoutId, int resourceId) {

    }

    /**
     * ******************** BEGIN BUILDER CLASS **************************************
     */

    public static class Builder {
        private final int listLayoutId;
        private HashMap itemViewTypes;
        private LinkedHashMap<Integer, ToastMenuItem> items;
        private ToastMenuFooterItem footerItem;
        private Level logLevel;
        private int width;
        private int selectedPosition;



        public Builder() {
            listLayoutId = 0;
            items = new LinkedHashMap<>();
            itemViewTypes = new HashMap();
        }

        public Builder(int listLayoutId) {
            this.listLayoutId = listLayoutId;
            items = new LinkedHashMap<>();
            itemViewTypes = new HashMap();
        }

        /**
         * Adds ToastMenuItem to the Side Menu.  Will display in the order they were added.
         *
         * @param item
         * @return
         */
        public Builder addMenuItem(ToastMenuItem item) {
            items.put(item.getMenuId(), item);
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
         * Sets the width in dp of the NavigationDrawer when open.
         * Default is 300dp
         *
         * @param width dp
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
            return new SideOfToast(this);
        }

        public Builder setSelected(int position) {
            this.selectedPosition = position;
            return this;
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

    public class SetSelectedItemEvent {
        private final int position;

        public SetSelectedItemEvent(int position) {
            this.position = position;
        }

        public int getPosition() {
            return position;
        }
    }
}

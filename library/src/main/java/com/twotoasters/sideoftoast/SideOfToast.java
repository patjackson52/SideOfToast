package com.twotoasters.sideoftoast;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.twotoasters.sideoftoast.items.ToastMenuItem;

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

    private static final String STATUS_BAR_HEIGHT = "status_bar_height";
    private static final String DIMEN = "dimen";
    private static final String ANDROID = "android";
    private static final int STATUS_BAR_HEIGHT_ADJUSTMENT = 2;

    public int getListLayoutId() {
        return listLayoutId;
    }


    public interface ReadyForToast {
        void toastMenuItemClicked(int position);
    }

    private static final String TAG = "SideOfToast";
    private static final int DEFAULT_WIDTH = 300;

    private final int listLayoutId;
    private final HashMap itemViewTypes;
    private LinkedHashMap<Integer, ToastMenuItem> items;
    private int width;
    private int selectedPosition;
    private static Level logLevel = Level.OFF;
    private final boolean slidingContent;
    private final boolean includeActionBarInContent;

    private SideOfToast(Builder builder) {
        items = builder.items;

        listLayoutId = builder.listLayoutId;
        this.itemViewTypes = builder.itemViewTypes;
        this.width = builder.width;

        logLevel = (builder.logLevel != null)
                ? builder.logLevel
                : Level.OFF;
        this.selectedPosition = builder.selectedPosition;
        this.slidingContent = builder.slidingContent;
        this.includeActionBarInContent = builder.drawerCoversActionBar;
    }


    public static void log(String message) {
        Logger.getLogger(TAG).log(logLevel, message);
    }

    /**
     * Inserts a NavigationDrawer View in the view hierarchy for the supplied activity.
     * Then attaches a fragment containing the Nav Menu
     *
     * @param activity Activity that will have Side Nav Menu added to it's view hierarchy
     * @return SideOfToast
     */
    public SideOfToast create(Activity activity) {
        convertWidthToDip(activity);

        DrawerLayout.LayoutParams lp = new DrawerLayout.LayoutParams(
                width,
                LinearLayout.LayoutParams.MATCH_PARENT);

        lp.gravity = Gravity.START;

        final FrameLayout fl = new FrameLayout(activity);

        DrawerLayout drawerLayout = new DrawerLayout(activity);

        fl.setId(R.id.drawer_contents);
        fl.setLayoutParams(lp);

        final ViewGroup firstViewInLayout;
        final ViewGroup activityRoot;

        if (includeActionBarInContent) {
            firstViewInLayout = (ViewGroup) ((ViewGroup) activity
                    .getWindow().getDecorView()).getChildAt(0);
            activityRoot = (ViewGroup) activity.getWindow().getDecorView();
            fl.setPadding(0, getStatusBarHeight(activity), 0, 0);
        } else {
            firstViewInLayout = (ViewGroup) ((ViewGroup) activity
                    .findViewById(android.R.id.content)).getChildAt(0);
            activityRoot = (ViewGroup) activity
                    .findViewById(android.R.id.content);
        }
        activityRoot.removeView(firstViewInLayout);

        setupDrawerLayout(drawerLayout, firstViewInLayout, fl, activityRoot);

        addSideNavFragment(activity);

        log(activity.getString(R.string.log_create_finished));
        return this;
    }

    private void convertWidthToDip(Activity activity) {
        width = (width == 0) ? DEFAULT_WIDTH : width;
        width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                width, activity.getResources().getDisplayMetrics());
    }

    private void setupDrawerLayout(final DrawerLayout drawerLayout,
                                   final ViewGroup mainView,
                                   final ViewGroup drawerView,
                                   final ViewGroup activityRoot) {
        drawerLayout.addView(mainView,
                new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT)
        );
        drawerLayout.addView(drawerView);
        drawerLayout.setId(R.id.drawer_layout);

        activityRoot.addView(drawerLayout);

    }

    private int getStatusBarHeight(Activity activity) {
        int result = 0;
        int resourceId = activity.getResources().getIdentifier(STATUS_BAR_HEIGHT,
                DIMEN,
                ANDROID);
        if (resourceId > 0) {
            //need to subtract two for some reason
            result = activity.getResources().getDimensionPixelSize(resourceId)
                    - STATUS_BAR_HEIGHT_ADJUSTMENT;
        }
        return result;
    }

    /**
     * Adds SideNavFragment to the Navigation drawer.
     *
     * @param activity reference to current activity
     */
    private void addSideNavFragment(Activity activity) {
        FragmentManager fm = activity.getFragmentManager();

        Fragment fragment = fm.findFragmentByTag(SideNavFragment.TAG);
        if (fragment == null) {
            fragment =  SideNavFragment.getInstance(this);
        }

        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.drawer_contents,
                fragment,
                SideNavFragment.TAG);
        fragmentTransaction.commit();
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

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void updateStringResource(int id, int layoutId, String str) {
        ToastMenuItem tmp = items.get(Integer.valueOf(id));
        if (tmp != null) {
            tmp.updateTextMap(layoutId, str);
        }
    }

    public void updateImageResource(int menuId, int layoutId, int resourceId) {
        ToastMenuItem tmp = items.get(Integer.valueOf(menuId));
        if (tmp != null) {
            tmp.updateImageMap(layoutId, resourceId);
        }
    }

    public boolean isSlidingContent() {
        return slidingContent;
    }

    public boolean isIncludeActionBarInContent() {
        return includeActionBarInContent;
    }

    /**
     * ******************** BEGIN BUILDER CLASS **************************************.
     */

    public static class Builder {
        private final int listLayoutId;
        private HashMap itemViewTypes;
        private LinkedHashMap<Integer, ToastMenuItem> items;
        private Level logLevel;
        private int width;
        private int selectedPosition;
        private boolean slidingContent;
        private boolean drawerCoversActionBar = true;


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
         * @param item pre-configured ToastMenuItem
         * @return instance of Builder
         */
        public Builder addMenuItem(ToastMenuItem item) {
            items.put(item.getMenuId(), item);
            return this;
        }

        public Builder addItemViewType(final int type, final int layoutResourceId) {
            this.itemViewTypes.put(type, layoutResourceId);
            return this;
        }

        /**
         * Logging level from Log class constants.
         * Log.VERBOSE, Log.ERROR, etc
         *
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
         * @return instance of Builder
         */
        public Builder setWidth(int width) {
            this.width = width;
            return this;
        }

        /**
         * Setting true slides the content view to the side as the drawer opens.
         *
         * @param slidingContent true if opening drawer is to slide activity view to the right.
         * @return instance of builder
         */
        public Builder setSlidingContent(boolean slidingContent) {
            this.slidingContent = slidingContent;
            return this;
        }

        /**
         * If false the actionbar will not be covered up by the drawer.  Defaults to true.
         * @param drawerCoversActionBar
         * @return
         */
        public Builder drawerCoversActionBar(boolean drawerCoversActionBar) {
            this.drawerCoversActionBar = drawerCoversActionBar;
            return this;
        }

        public SideOfToast build() {
            return new SideOfToast(this);
        }

        public Builder setSelected(int position) {
            this.selectedPosition = position;
            return this;
        }
    }
}

package com.twotoasters.toastnavmenu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by patrickjackson on 7/27/14.
 */
public class Toaster {
    private final int listLayoutId;
    private final HashMap itemViewTypes;
    private final NavMenuItem items[];
    private final SIDE side;
    DrawerLayout drawerLayout;

    enum SIDE {
        LEFT,
        RIGHT
    }


    private Toaster(ToasterBuilder toasterBuilder) {
        items = (NavMenuItem[]) toasterBuilder.items.toArray();
        listLayoutId = toasterBuilder.listLayoutId;
        side = toasterBuilder.side;
        this.itemViewTypes = toasterBuilder.itemViewTypes;
    }

    public View create(FragmentActivity activity, View rootView) {
        final ViewGroup newRootView = (ViewGroup) ((ViewGroup) activity
                .findViewById(android.R.id.content)).getChildAt(0);

        drawerLayout = new DrawerLayout(activity);

        drawerLayout.setLayoutParams(
                new DrawerLayout.LayoutParams(
                        DrawerLayout.LayoutParams.MATCH_PARENT,
                        DrawerLayout.LayoutParams.MATCH_PARENT
                ));
        drawerLayout.setId(R.id.drawer_layout);
        drawerLayout.addView(rootView);

        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        Fragment fragment = (Fragment) new NavigationDrawerFragment();
        fragmentTransaction.add(drawerLayout.getId(),
                fragment,
                "NavMenu");
        fragmentTransaction.commit();

        return drawerLayout;
    }

    public static class ToasterBuilder {
        private final int listLayoutId;
        private HashMap itemViewTypes;
        private ArrayList<NavMenuItem> items;
        private SIDE side = SIDE.LEFT;


        public ToasterBuilder(int listLayoutId) {
            this.listLayoutId = listLayoutId;
            items = new ArrayList<>();
            itemViewTypes = new HashMap();
        }

        public ToasterBuilder item(NavMenuItem item) {
            items.add(item);
            return this;
        }

        /**
         * Set side menu to Left or Right side - optional
         *
         * @param side
         * @return
         */
        public ToasterBuilder setSide(SIDE side) {
            this.side = side;
            return this;
        }

        public ToasterBuilder addItemViewType(final int type, final int layoutResourceId) {
            this.itemViewTypes.put(type, layoutResourceId);
            return this;
        }


        public Toaster build() throws NoItemViewTypesException {
            if (itemViewTypes.size() < 1) {
                throw new NoItemViewTypesException();
            }
            return new Toaster(this);
        }

        public static class NoItemViewTypesException extends Exception {
        }
    }

}

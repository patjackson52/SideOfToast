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
import java.util.HashMap;

/**
 * Created by patrickjackson on 7/27/14.
 */
public class Toaster implements Serializable {
    private final int listLayoutId;
    private final HashMap itemViewTypes;
    private MenuItem[] items;
    private final SIDE side;
    DrawerLayout drawerLayout;

    enum SIDE {
        LEFT,
        RIGHT
    }


    private Toaster(ToasterBuilder toasterBuilder) {
        items = new MenuItem[toasterBuilder.items.size()];
        toasterBuilder.items.toArray(items);
        listLayoutId = toasterBuilder.listLayoutId;
        side = toasterBuilder.side;
        this.itemViewTypes = toasterBuilder.itemViewTypes;
    }

    public View create(FragmentActivity activity) {
        final ViewGroup firstViewInLayout = (ViewGroup) ((ViewGroup) activity
                .findViewById(android.R.id.content)).getChildAt(0);
        final ViewGroup activityRoot = (ViewGroup) ((ViewGroup) activity
                .findViewById(android.R.id.content));

        drawerLayout = new DrawerLayout(activity);


        DrawerLayout.LayoutParams lp = new DrawerLayout.LayoutParams(
                700 , LinearLayout.LayoutParams.MATCH_PARENT);

        lp.gravity=Gravity.START;


        final FrameLayout fl = new FrameLayout(activity);
        fl.setId(R.id.drawer_contents);
        fl.setLayoutParams(lp);

        activityRoot.removeView(firstViewInLayout);
        drawerLayout.addView(firstViewInLayout, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
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

    public MenuItem[] getItems() {
        return items;
    }

    public SIDE getSide() {
        return side;
    }

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    public int getLayoutForType(int type) {
        return (int) getItemViewTypes().get(type);
    }

    public static class ToasterBuilder {
        private final int listLayoutId;
        private HashMap itemViewTypes;
        private ArrayList<MenuItem> items;
        private SIDE side = SIDE.LEFT;


        public ToasterBuilder(int listLayoutId) {
            this.listLayoutId = listLayoutId;
            items = new ArrayList<>();
            itemViewTypes = new HashMap();
        }

        public ToasterBuilder item(MenuItem item) {
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


        public Toaster build() {
            if (itemViewTypes.size() < 1) {
//                throw new NoItemViewTypesException();
            }
            return new Toaster(this);
        }

        public static class NoItemViewTypesException extends Exception {
        }
    }

}

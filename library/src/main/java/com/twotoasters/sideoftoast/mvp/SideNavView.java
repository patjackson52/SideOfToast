package com.twotoasters.sideoftoast.mvp;

import android.app.ActionBar;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.twotoasters.sideoftoast.Events;
import com.twotoasters.sideoftoast.R;
import com.twotoasters.sideoftoast.SideNavItemAdapter;
import com.twotoasters.sideoftoast.SideOfToast;
import com.twotoasters.sideoftoast.items.ToastMenuFooterItem;
import com.twotoasters.sideoftoast.items.ToastMenuItem;


public class SideNavView extends FragmentViewImpl<Fragment> {

    private DrawerLayout drawerLayout;
    private ListView drawerListView;
    private View fragmentContainerView;
    private View contentView;
    private View footer;
    private boolean slidingContent;

    /**
     * Helper component that ties the action bar to the navigation drawer.
     */
    private ActionBarDrawerToggle drawerToggle;

    public SideNavView(Fragment fragment) {
        super(fragment);
        setupWidgets();
        setUpNavDrawer();
    }

    public void setupWidgets() {
        drawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        drawerListView = (ListView) findViewInFragment(R.id.sideOfToastListView);
        drawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BusProvider.post(new Events.ToastMenuItemClickEvent(position,
                        ((ToastMenuItem) drawerListView
                                .getAdapter().getItem(position)).getMenuId()));
                drawerLayout.closeDrawers();
            }
        });
        contentView = drawerLayout.getChildAt(0);
    }


    public boolean isDrawerOpen() {
        return drawerLayout != null && drawerLayout.isDrawerOpen(fragmentContainerView);
    }

    public void setFooterLayout(ToastMenuFooterItem footerItem) {
        if (footer != null) {
            drawerListView.removeFooterView(footer);
        }

        footer = View.inflate(getActivity(),
                footerItem.getLayoutId(),
                null);
        drawerListView.addFooterView(footer, null, footerItem.isEnabled());

        footerItem.setImageOrText(footer, -1);
    }


    public void setMenuItems(final SideOfToast sideOfToast) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                drawerListView.setAdapter(
                        new SideNavItemAdapter(getActivity(),
                                (int) sideOfToast.getItemViewTypes().values().iterator().next(),
                                sideOfToast)
                );
            }
        });
    }

    public void setCurrentSelectedPosition(int position) {
        if (drawerListView != null) {
            drawerListView.setItemChecked(position, true);
        }
    }


    public void setUpNavDrawer() {
        fragmentContainerView = getActivity().findViewById(R.id.drawer_layout);

        // set a custom shadow that overlays the main content when the drawer opens
//        this.drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the navigation drawer and the action bar app icon.
        drawerToggle = new ActionBarDrawerToggle(
                getActivity(),                    /* host Activity */
                SideNavView.this.drawerLayout,    /* DrawerLayout object */
                R.drawable.ic_navigation_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
                R.string.navigation_drawer_close  /* "close drawer" description for accessibility */
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (!getFragment().isAdded()) {
                    return;
                }

                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!getFragment().isAdded()) {
                    return;
                }

                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (!slidingContent) return;
                float moveFactor = (drawerListView.getWidth() * slideOffset);
                contentView.setTranslationX(moveFactor);
            }
        };

        this.drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                drawerToggle.syncState();
            }
        });

        this.drawerLayout.setDrawerListener(drawerToggle);

    }

    public void setDrawerToggleConfiguration(Configuration newConfig) {
        drawerToggle.onConfigurationChanged(newConfig);
    }

    public boolean drawerToggleOnOptionsItemSelected(MenuItem item) {
        return drawerToggle.onOptionsItemSelected(item);
    }

    public void setSlidingContent(boolean slidingContent) {
        this.slidingContent = slidingContent;
    }
}

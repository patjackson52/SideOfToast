package com.twotoasters.sideoftoast.mvp;

import android.app.Fragment;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.twotoasters.sideoftoast.Events;
import com.twotoasters.sideoftoast.R;
import com.twotoasters.sideoftoast.SideNavItemAdapter;
import com.twotoasters.sideoftoast.SideOfToast;
import com.twotoasters.sideoftoast.items.ToastMenuItem;


public class SideNavView extends FragmentViewImpl<Fragment> {

    private DrawerLayout drawerLayout;
    private ListView drawerListView;
    private View fragmentContainerView;
    private View contentView;
    private boolean slidingContent;

    private ActionBarDrawerToggle drawerToggle;

    public SideNavView(Fragment fragment) {
        super(fragment);
        refresh();
    }

    public void refresh() {
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

        drawerToggle = new ActionBarDrawerToggle(
                getActivity(),                    /* host Activity */
                SideNavView.this.drawerLayout,    /* DrawerLayout object */
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

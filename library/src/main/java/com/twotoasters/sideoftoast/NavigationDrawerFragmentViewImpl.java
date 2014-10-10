package com.twotoasters.sideoftoast;

import android.app.ActionBar;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.twotoasters.sideoftoast.R;


public class NavigationDrawerFragmentViewImpl extends FragmentViewImpl<Fragment>
        implements NavigationDrawerFragmentView {

    private DrawerLayout drawerLayout;
    private ListView drawerListView;
    private View fragmentContainerView;

    /**
     * Helper component that ties the action bar to the navigation drawer.
     */
    private ActionBarDrawerToggle drawerToggle;

    public NavigationDrawerFragmentViewImpl(Fragment fragment) {
        super(fragment);
        setupWidgets();
        setUpNavDrawer();
    }

    public void setupWidgets() {
        drawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        drawerListView = (ListView) findViewInFragment(R.id.drawerListView);
        drawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BusProvider.post(new ToastMenuItemClickEvent(position));
            }
        });

    }


    public boolean isDrawerOpen() {
        return drawerLayout != null && drawerLayout.isDrawerOpen(fragmentContainerView);
    }

    @Override
    public void setFooterLayout(ToastMenuFooterItem footerItem) {
        View footer = View.inflate(getActivity(),
               footerItem.getLayoutId(),
               null);
       drawerListView.addFooterView(footer, null, footerItem.isEnabled());

        footerItem.setImageAndText(footer, -1);
    }


    @Override
    public void setMenuItems(final ToastMenuItem[] items,
                             final SideOfToast sideOfToast) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                drawerListView.setAdapter(
                        new NavDrawerItemAdapter(getActivity(),
                                (int) sideOfToast.getItemViewTypes().values().iterator().next(),
                                sideOfToast)
                );
            }
        });
    }

    @Override
    public void setCurrentSelectedPosition(int position) {
        if (drawerListView != null) {
            drawerListView.setItemChecked(position, true);
        }

        if (drawerLayout != null) {
//            drawerLayout.closeDrawer(fragmentContainerView);
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
                NavigationDrawerFragmentViewImpl.this.drawerLayout,                    /* DrawerLayout object */
                R.drawable.ic_navigation_drawer,             /* nav drawer image to replace 'Up' caret */
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

    @Override
    public boolean drawerToggleOnOptionsItemSelected(MenuItem item) {
        return drawerToggle.onOptionsItemSelected(item);
    }

    public static class ToastMenuItemClickEvent {
        final int position;

        public ToastMenuItemClickEvent(int position) {
            this.position = position;
        }

        public int getPosition() {
            return position;
        }
    }
}

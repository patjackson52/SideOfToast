package com.twotoasters.sideoftoast;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class NavigationDrawerFragment extends Fragment {

    public static final String TAG = "NavigationDrawer";
    public static final String TOASTER_KEY = "toaster_key";

    NavigationDrawerFragmentPresenter presenter;
    Bundle savedInstanceState;

    public static NavigationDrawerFragment getInstance(SideOfToast sideOfToast) {
        NavigationDrawerFragment fragment = new NavigationDrawerFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TOASTER_KEY, sideOfToast);
        fragment.setArguments(bundle);
        return fragment;
    }

    public NavigationDrawerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (presenter == null) {
            presenter = createPresenter();
            presenter.restoreInstanceState(savedInstanceState);
            // Some older devices don't have the presenter created before the options menu is created
            getActivity().invalidateOptionsMenu();
        }
        BusProvider.register(presenter);
    }

    @Override
    public void onStop() {
        BusProvider.unregister(presenter);
        super.onStop();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        if (presenter != null) {
            presenter.onPrepareOptionsMenu(menu);
        }
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        presenter.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        presenter.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return presenter.onOptionsItemSelected(item)
                || super.onOptionsItemSelected(item);
    }

    private NavigationDrawerFragmentPresenter createPresenter() {
        int startPosition = 0;
        return new NavigationDrawerFragmentPresenter(
                new NavigationDrawerFragmentModel(
                        (SideOfToast) getArguments().getSerializable(TOASTER_KEY),
                        startPosition),
                new NavigationDrawerFragmentView(this));
    }

    public void refreshNavDrawerList() {
        presenter.refreshNavMenu();
    }

}

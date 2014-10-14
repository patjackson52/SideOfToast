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

import com.twotoasters.sideoftoast.mvp.BusProvider;
import com.twotoasters.sideoftoast.mvp.SideNavModel;
import com.twotoasters.sideoftoast.mvp.SideNavPresenter;
import com.twotoasters.sideoftoast.mvp.SideNavView;


public class SideNavFragment extends Fragment {

    public static final String TAG = "SideNavFragment";
    public static final String TOASTER_KEY = "toaster_key";
    public static final String LAYOUT_ID = "layout_id_key";

    private SideNavPresenter presenter;
    private Bundle savedInstanceState;

    public static SideNavFragment getInstance(SideOfToast sideOfToast) {
        SideNavFragment fragment = new SideNavFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TOASTER_KEY, sideOfToast);
        bundle.putInt(LAYOUT_ID, (sideOfToast.getListLayoutId() == 0)
                ? R.layout.fragment_navigation_drawer
                : sideOfToast.getListLayoutId());
        fragment.setArguments(bundle);
        return fragment;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(getArguments().getInt(LAYOUT_ID), container, false);
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


    private SideNavPresenter createPresenter() {
        int startPosition = 0;
        return new SideNavPresenter(
                new SideNavModel(
                        (SideOfToast) getArguments().getSerializable(TOASTER_KEY),
                        startPosition),
                new SideNavView(this));
    }
}

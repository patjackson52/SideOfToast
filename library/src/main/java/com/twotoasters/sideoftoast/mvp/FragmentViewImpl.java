package com.twotoasters.sideoftoast.mvp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.view.View;

import java.lang.ref.WeakReference;

public abstract class FragmentViewImpl<T extends Fragment> implements FragmentView {


    WeakReference<T> fragmentRef;

    public FragmentViewImpl(T fragment) {
        fragmentRef = new WeakReference<T>(fragment);
    }

    public T getFragment() {
        return fragmentRef.get();
    }

    public boolean hasFragmentRef() {
        return fragmentRef != null && fragmentRef.get() != null;
    }

    public View findViewInFragment(int id) {
        View rootView = getFragmentView();
        if (rootView != null) {
            return rootView.findViewById(id);
        }
        return null;
    }

    public View getFragmentView() {
        if (hasFragmentRef()) {
            return getFragment().getView();
        }
        return null;
    }

    public Activity getActivity() {
        if (hasFragmentRef()) {
            return getFragment().getActivity();
        }
        return null;
    }


    protected ActionBar getActionBar() {
        if (getActivity() != null) {
            return getActivity().getActionBar();
        }
        return null;
    }

    public void invalidateOptionsMenu() {
        Activity activity = getActivity();
        if (activity != null) {
            activity.invalidateOptionsMenu();
        }
    }

}

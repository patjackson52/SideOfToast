package com.twotoasters.toastnavmenu;

import android.app.ActionBar;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import java.lang.ref.WeakReference;

/**
 * Created by patrickjackson on 4/22/14.
 */
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

    public FragmentActivity getActivity() {
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

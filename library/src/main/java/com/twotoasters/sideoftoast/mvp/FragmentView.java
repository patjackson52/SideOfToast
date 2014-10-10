package com.twotoasters.sideoftoast.mvp;

import android.app.Activity;
import android.support.v4.app.Fragment;

public interface FragmentView {

    Fragment getFragment();

    Activity getActivity();

    void setupWidgets();
}

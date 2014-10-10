package com.twotoasters.sideoftoast;

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * Created by patrickjackson on 5/1/14.
 */
public interface FragmentView {

    Fragment getFragment();

    Activity getActivity();

    void setupWidgets();
}

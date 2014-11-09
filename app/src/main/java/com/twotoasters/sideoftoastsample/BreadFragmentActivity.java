package com.twotoasters.sideoftoastsample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class BreadFragmentActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SideNavMenu.addBreadMenu(this);
    }

}

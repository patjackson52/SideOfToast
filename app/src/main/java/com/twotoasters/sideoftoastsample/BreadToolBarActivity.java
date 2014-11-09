package com.twotoasters.sideoftoastsample;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

public class BreadToolBarActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_with_toolbar);
        SideNavMenu.addBreadMenu(this);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_type_menu, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.activity:
                startActivity(new Intent(this, BreadActivity.class));
                break;
            case R.id.fragment_activity:
                startActivity(new Intent(this, BreadFragmentActivity.class));
                break;
            case R.id.action_bar_activity:
                startActivity(new Intent(this, BreadActionBarActivity.class));
                break;
            case R.id.toolbar_activity:
                startActivity(new Intent(this, BreadToolBarActivity.class));
                break;
        }
        return super.onMenuItemSelected(featureId, item);
    }
}

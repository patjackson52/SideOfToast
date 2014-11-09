package com.twotoasters.sideoftoastsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Example with App Compat's ActionbarAcitivity
 */
public class BreadActionBarActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        SideNavMenu.addBreadMenu(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_type_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
        return super.onOptionsItemSelected(item);
    }

}

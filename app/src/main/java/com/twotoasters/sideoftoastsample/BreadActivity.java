package com.twotoasters.sideoftoastsample;

import android.app.ActionBar;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class BreadActivity extends FragmentActivity {

    private static final int TYPES_OF_BREAD_ID = 0;
    private static final int PLAIN_TOAST_MENU_ID = 1;
    private static final int BAGEL_MENU_ID = 2;
    private static final int OTHER_TOAST_MENU_ID = 3;
    private static final int TOTALS_HEADER_ID = 4;
    private static final int TOTALS_ID = 5;

    private static final int TOAST_MENU_TYPE = 1;
    private static final int TOAST_MENU_HEADER_TYPE = 2;
    private static final int TOAST_MENU_FOOTER_TYPE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupWidgets();
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
        switch (item.getItemId()){
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

    private ImageView toast;

    private void setupWidgets() {
        toast = (ImageView) findViewById(R.id.toast1);
        toast.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDrag(data, shadowBuilder, v, 0);
                v.setVisibility(View.INVISIBLE);
                return false;
            }
        });
    }

    class MyDragListener implements View.OnDragListener {
        Drawable enterShape = getResources().getDrawable(R.drawable.drop_target);
        Drawable normalShape = getResources().getDrawable(R.drawable.shape);

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundDrawable(enterShape);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundDrawable(normalShape);
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    LinearLayout container = (LinearLayout) v;
                    container.addView(view);
                    view.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundDrawable(normalShape);
                default:
                    break;
            }
            return true;
        }
    }

}

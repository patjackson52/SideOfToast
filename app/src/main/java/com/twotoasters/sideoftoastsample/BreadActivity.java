package com.twotoasters.sideoftoastsample;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewPropertyAnimator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class BreadActivity extends FragmentActivity {

    ImageView toast1;
    ImageView toast2;
    ImageView toast3;
    @InjectView(R.id.toaster) ImageView toaster;
    @InjectView(R.id.container) RelativeLayout container;
    private int numToasted = 0;
    private Random random;
    private DisplayMetrics metrics;
    private int spaceAtTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        random = new Random();
        metrics = new DisplayMetrics();
        spaceAtTop = getStatusBarHeight() + getToolbarHeightPx(this);

        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        setupWidgets();
        SideNavMenu.addBreadMenu(this);

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

    public class ToastTouchListener implements OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.setOnDragListener(new MyDragListener());
            v.startDrag(data, shadowBuilder, v, 0);
            v.setVisibility(View.INVISIBLE);
            return false;
        }
    }

    private void setupWidgets() {
        ButterKnife.inject(this);
        toaster.setOnDragListener(new MyDragListener());

        toast1 = getToast(R.drawable.dark_untoasted_lg);
        toast2 = getToast(R.drawable.light_untoasted_lg);
        toast3 = getToast(R.drawable.light_untoasted_lg);

        container.addView(toast1);
        container.addView(toast2);
        container.addView(toast3);
    }

    private ImageView getToast(@DrawableRes int drawableRes) {
        ImageView imageView = new ImageView(this);
        LayoutParams params = container.getLayoutParams();
        params.height = LayoutParams.WRAP_CONTENT;
        params.width = LayoutParams.WRAP_CONTENT;
        imageView.setImageResource(drawableRes);
        imageView.setLayoutParams(params);
        imageView.setX(getRandomX(imageView.getWidth()));
        imageView.setY(getRandomY(imageView.getHeight()));
        imageView.setOnTouchListener(new ToastTouchListener());
        return imageView;
    }

    private int getRandomX(int viewWidth) {
        return random.nextInt(metrics.widthPixels - viewWidth);
    }


    private int getRandomY(int viewHeight) {
        int y = random.nextInt(metrics.heightPixels - spaceAtTop) + spaceAtTop;
        if (y > metrics.heightPixels - spaceAtTop - toaster.getHeight()) {
            y-= toaster.getHeight() + spaceAtTop;
        }
        return y;
    }

    class MyDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(final View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    int loc1[] = new int[2];
                    int toasterLoc[] = new int[2];

                    final View draggedView = (View) event.getLocalState();
                    toaster.getLocationOnScreen(toasterLoc);
                    draggedView.getLocationOnScreen(loc1);

                    int startX = toasterLoc[0];
                    int startY = toasterLoc[1] - toaster.getHeight() + spaceAtTop;

                    draggedView.setX(startX);
                    draggedView.setY(startY);

                    Random random = new Random();

                    ViewPropertyAnimator anim1 = draggedView.animate()
                            .x(getRandomX(toaster.getWidth()))
                            .y(getRandomY(toaster.getHeight()))
                            .rotationBy(random.nextInt(4000))
                            .setDuration(random.nextInt(2000) + 400)
                            .setInterpolator(new DecelerateInterpolator())
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationStart(Animator animation) {
                                    draggedView.setVisibility(View.VISIBLE);
                                    super.onAnimationStart(animation);
                                }
                            });

                    anim1.start();
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                default:
                    break;
            }

            return true;
        }

    }

    public static int getToolbarHeightPx(Context context) {
        int toolbarHeight;
        final TypedArray ta = context.getTheme().obtainStyledAttributes(new int[]{android.R.attr.actionBarSize});
        toolbarHeight = (int) ta.getDimension(0, 0);
        ta.recycle();
        return toolbarHeight;
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}

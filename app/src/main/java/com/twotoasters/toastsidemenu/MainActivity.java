package com.twotoasters.toastsidemenu;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.twotoasters.toastnavmenu.ToastMenuItem;
import com.twotoasters.toastnavmenu.SideOfToast;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        addNavMenu();
    }

    private void addNavMenu() {
        ToastMenuItem messagesToastMenuItem =
                new ToastMenuItem.Builder(0, 0)
                        .addText(R.id.navmenuitem_label, R.string.menu_title_messages)
                        .addImage(R.id.navmenuitem_icon, R.drawable.selector_messages_menu_icon)
                        .build();

        ToastMenuItem homeToastMenuItem =
                new ToastMenuItem.Builder(0, 0)
                        .addText(R.id.navmenuitem_label, R.string.menu_title_home)
                        .addImage(R.id.navmenuitem_icon, R.drawable.selector_home_menu_icon)
                        .build();
        new SideOfToast.Builder(R.layout.navigation_drawer)
                .addItemViewType(0, R.layout.navmenu_item)
                .item(homeToastMenuItem)
                .item(messagesToastMenuItem)
                .build()
                .create(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}

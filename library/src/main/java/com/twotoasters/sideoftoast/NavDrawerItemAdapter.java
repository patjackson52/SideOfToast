package com.twotoasters.sideoftoast;

/**
 * Created by patrickjackson on 4/21/14.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class NavDrawerItemAdapter extends ArrayAdapter<ToastMenuItem> {


    private LayoutInflater inflater;
    private SideOfToast sideOfToast;

    public NavDrawerItemAdapter(Context context,
                                int textViewResourceId,
                                SideOfToast sideOfToast) {

        super(context, textViewResourceId, sideOfToast.getItems());
        this.inflater = LayoutInflater.from(context);
        this.sideOfToast = sideOfToast;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        ToastMenuItem toastMenuItem = this.getItem(position);

        view = inflater.inflate(sideOfToast.getLayoutForType(toastMenuItem.getItemViewType()), null);

        toastMenuItem.setImageAndText(view, position);


        return view;
    }

    @Override
    public boolean isEnabled(int position) {
        return getItem(position).isEnabled();
    }

    @Override
    public int getViewTypeCount() {
        return sideOfToast.getItemViewTypes().size();
    }

    @Override
    public int getItemViewType(int position) {
        return this.getItem(position).getItemViewType();
    }
}


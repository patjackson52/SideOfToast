package com.twotoasters.sideoftoast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.twotoasters.sideoftoast.items.ToastMenuItem;
import com.twotoasters.sideoftoast.items.Utils;

public class SideNavItemAdapter extends ArrayAdapter<ToastMenuItem> {


    private LayoutInflater inflater;
    private SideOfToast sideOfToast;

    public SideNavItemAdapter(Context context,
                              int textViewResourceId,
                              SideOfToast sideOfToast) {

        super(context, textViewResourceId, Utils.getArrayOfMenuItems(sideOfToast));
        this.inflater = LayoutInflater.from(context);
        this.sideOfToast = sideOfToast;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        ToastMenuItem toastMenuItem = this.getItem(position);

        view = inflater.inflate(sideOfToast.getLayoutForType(toastMenuItem.getItemViewType()), null);

        toastMenuItem.setImageOrText(view, position);


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


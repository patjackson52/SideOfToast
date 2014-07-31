package com.twotoasters.toastnavmenu;

/**
 * Created by patrickjackson on 4/21/14.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class NavDrawerItemAdapter extends ArrayAdapter<MenuItem> {


    private LayoutInflater inflater;
    private Toaster toaster;

    public NavDrawerItemAdapter(Context context,
                                int textViewResourceId,
                                Toaster toaster) {

        super(context, textViewResourceId, toaster.getItems());
        this.inflater = LayoutInflater.from(context);
        this.toaster = toaster;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        MenuItem menuItem = this.getItem(position);

        view = inflater.inflate(toaster.getLayoutForType(menuItem.getItemViewType()), null);

        for (Object id : menuItem.getTextMap().keySet()) {
            TextView tmp = (TextView) view.findViewById((Integer) id);
            tmp.setText(menuItem.getTextResourceForView((Integer) id));
        }
        return view;
    }


    @Override
    public int getViewTypeCount() {
        return toaster.getItemViewTypes().size();
    }

    @Override
    public int getItemViewType(int position) {
        return this.getItem(position).getItemViewType();
    }
}


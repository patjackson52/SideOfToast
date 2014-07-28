package com.twotoasters.toastnavmenu;

/**
 * Created by patrickjackson on 4/21/14.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NavDrawerItemAdapter extends ArrayAdapter<NavMenuItem> {


    private LayoutInflater inflater;

    public NavDrawerItemAdapter(Context context, int textViewResourceId, NavMenuItem[] objects) {
        super(context, textViewResourceId, objects);
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        NavMenuItem menuItem = this.getItem(position);
        if (menuItem.getType() == NavMenuItem.ITEM_TYPE) {
            view = getItemView(convertView, parent, menuItem);
        } else {
            view = getSectionView(convertView, parent, menuItem);
        }
        return view;
    }

    public View getItemView(View convertView, ViewGroup parentView, NavMenuItem navMenuItem) {

        NavMenuItemHolder navMenuItemHolder = null;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.navmenu_item, parentView, false);
            TextView labelView = (TextView) convertView
                    .findViewById(R.id.navmenuitem_label);
            ImageView iconView = (ImageView) convertView
                    .findViewById(R.id.navmenuitem_icon);

            navMenuItemHolder = new NavMenuItemHolder();
            navMenuItemHolder.labelView = labelView;
            navMenuItemHolder.iconView = iconView;

            convertView.setTag(navMenuItemHolder);
        }

        if (navMenuItemHolder == null) {
            navMenuItemHolder = (NavMenuItemHolder) convertView.getTag();
        }

        navMenuItemHolder.labelView.setText(navMenuItem.getLabel());
        navMenuItemHolder.iconView.setImageResource(navMenuItem.getIcon());


        return convertView;
    }

    public View getSectionView(View convertView, ViewGroup parentView,
                               NavMenuItem navMenuItem) {

        NavMenuSectionHolder navMenuItemHolder = null;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.navmenu_section, parentView, false);
            TextView labelView = (TextView) convertView
                    .findViewById(R.id.navmenusection_label);

            navMenuItemHolder = new NavMenuSectionHolder();
            navMenuItemHolder.labelView = labelView;
            convertView.setTag(navMenuItemHolder);
        }

        if (navMenuItemHolder == null) {
            navMenuItemHolder = (NavMenuSectionHolder) convertView.getTag();
        }

        navMenuItemHolder.labelView.setText(navMenuItem.getLabel());

        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return this.getItem(position).getType();
    }


    private static class NavMenuItemHolder {
        private TextView labelView;
        private ImageView iconView;
    }

    private class NavMenuSectionHolder {
        private TextView labelView;
    }
}


package com.twotoasters.sideoftoast.items;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.twotoasters.sideoftoast.R;
import com.twotoasters.sideoftoast.SideOfToast;

import java.util.HashMap;

public class ValueMappedItem {

    private HashMap<Integer, Integer> imageMap;
    private HashMap<Integer, String> textMap;

    protected ValueMappedItem(HashMap<Integer, String> textMap,
                              HashMap<Integer, Integer> imageMap) {
        this.imageMap = imageMap;
        this.textMap = textMap;
    }

    public void setImageOrText(View view, int position) {
        for (Object id : getTextMap().keySet()) {
            TextView tmp = (TextView) view.findViewById((Integer) id);
            if (tmp != null) {
                tmp.setText(getStringForView((Integer) id));
            } else {
                SideOfToast.log(view.getContext().getString(R.string.log_bad_view_id) + position);
            }
        }

        for (Object id : getImageMap().keySet()) {
            ImageView tmp = (ImageView) view.findViewById((Integer) id);
            if (tmp != null) {
                tmp.setImageResource(getImageResourceForView((Integer) id));
            } else {
                SideOfToast.log(view.getContext().getString(R.string.log_bad_view_id) + position);
            }
        }
    }

    public void updateTextMap(int layoutId, String str) {
        for (int id : getTextMap().keySet()) {
            if (id == layoutId) {
                getTextMap().put(layoutId, str);
                break;
            }

        }
    }

    public void updateImageMap(int layoutId, int resourceId) {
            for (int id : getImageMap().keySet()) {
            if (id == layoutId) {
                getImageMap().put(layoutId, resourceId);
                break;
            }
        }
    }

    public String getStringForView(Integer viewId) {
        return textMap.get(viewId);
    }

    public int getImageResourceForView(Integer viewId) {
        return imageMap.get(viewId);
    }

    public HashMap<Integer, Integer> getImageMap() {
        return imageMap;
    }

    public void setImageMap(HashMap<Integer, Integer> imageMap) {
        this.imageMap = imageMap;
    }

    public HashMap<Integer, String> getTextMap() {
        return textMap;
    }

    public void setTextMap(HashMap<Integer, String> textMap) {
        this.textMap = textMap;
    }

}

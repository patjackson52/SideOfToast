package com.twotoasters.sideoftoast;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.twotoasters.sideoftoast.SideOfToast;
import com.twotoasters.sideoftoast.R;

import java.util.HashMap;

/**
 * Created by patrickjackson on 8/11/14.
 */
public abstract class ValueMappedItem {

    private HashMap<Integer, Integer> imageMap;
    private HashMap<Integer, String> textMap;

    protected ValueMappedItem(HashMap<Integer, String> textMap,
                              HashMap<Integer, Integer> imageMap) {
        this.imageMap = imageMap;
        this.textMap = textMap;
    }

    public void setImageAndText(View view, int position) {
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

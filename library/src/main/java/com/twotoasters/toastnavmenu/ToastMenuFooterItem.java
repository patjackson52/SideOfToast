package com.twotoasters.toastnavmenu;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by patrickjackson on 8/7/14.
 */
public class ToastMenuFooterItem extends ValueMappedItem {
    private final int layoutId;
    private final boolean enabled;

    private ToastMenuFooterItem(Builder builder) {
        super(builder.textMap, builder.imageMap);
        this.layoutId = builder.layoutId;
        this.enabled = builder.enabled;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public boolean isEnabled() {
        return enabled;
    }


    public static class Builder {
        private final int layoutId;
        private boolean enabled = true;
        private HashMap<Integer, Integer> imageMap;
        private HashMap<Integer, String> textMap;

        public Builder(int layoutId) {
            this.imageMap = new HashMap();
            this.textMap = new HashMap();
            this.layoutId = layoutId;
        }

        public ToastMenuFooterItem build() {
            return new ToastMenuFooterItem(this);
        }

        public Builder setEnabled(boolean b) {
            enabled = b;
            return this;
        }


        public Builder addImage(int resourceId, int drawableId) {
            imageMap.put(resourceId, drawableId);
            return this;
        }

        public Builder addText(Integer resourceId, Integer textResourceId, Context context) {
            textMap.put(resourceId, context.getString(textResourceId));
            return this;
        }

        public Builder addText(Integer resourceId, String string) {
            textMap.put(resourceId, string);
            return this;
        }
    }
}
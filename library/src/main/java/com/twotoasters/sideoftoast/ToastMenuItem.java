package com.twotoasters.sideoftoast;

import android.content.Context;

import com.twotoasters.sideoftoast.R;

import java.util.HashMap;

public class ToastMenuItem extends ValueMappedItem implements Comparable<ToastMenuItem> {
    private final int menuId;
    private final int itemViewType;
    private int titleResId;
    private boolean enabled;

    private ToastMenuItem(Builder builder) {
        super(builder.textMap, builder.imageMap);
        menuId = builder.position;
        titleResId = builder.titleResId;
        enabled = builder.enabled;
        itemViewType = builder.itemViewType;
    }

    public int getPosition() {
        return menuId;
    }

    public int getItemViewType() {
        return itemViewType;
    }

    public int getTitleResId() {
        return titleResId;
    }


    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public int compareTo(ToastMenuItem another) {
        return this.getPosition() - another.getPosition();
    }


    public static class Builder {
        private final int position;
        private final int itemViewType;
        private int titleResId;
        private HashMap<Integer, Integer> imageMap;
        private HashMap<Integer, String> textMap;
        private HashMap<Integer, Integer> textIdMap;
        private boolean enabled = true;

        public Builder(int position, int itemViewType) {
            this.position = position;
            this.itemViewType = itemViewType;
            this.imageMap = new HashMap();
            this.textMap = new HashMap();
            this.textIdMap = new HashMap();
        }

        public Builder addImage(int resourceId, int drawableId) {
            imageMap.put(resourceId, drawableId);
            return this;
        }


        public Builder addText(Integer resourceId, Integer textResourceId) {
            textIdMap.put(resourceId, textResourceId);
            return this;
        }

        public Builder addText(Integer resourceId, String string) {
            textMap.put(resourceId, string);
            return this;
        }

        public Builder setTitle(int titleResId) {
            this.titleResId = titleResId;
            return this;
        }

        public Builder setEnabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }


        public ToastMenuItem build(Context context) {
            for (Integer id : textIdMap.keySet()) {
                try {
                    textMap.put(id, context.getString(textIdMap.get(id)));
                } catch (Exception e) {
                    SideOfToast.log(context.getString(R.string.error_bad_string_map));
                }
            }
            return new ToastMenuItem(this);
        }

    }
}

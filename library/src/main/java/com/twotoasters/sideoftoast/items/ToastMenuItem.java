package com.twotoasters.sideoftoast.items;

import android.content.Context;

import com.twotoasters.sideoftoast.R;
import com.twotoasters.sideoftoast.SideOfToast;

import java.io.Serializable;
import java.util.HashMap;

public final class ToastMenuItem extends ValueMappedItem
        implements Comparable<ToastMenuItem>, Serializable {
    private final int menuId;
    private final int itemViewType;
    private int titleResId;
    private boolean enabled;

    private ToastMenuItem(Builder builder) {
        super(builder.textMap, builder.imageMap);
        menuId = builder.menuId;
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

    public int getMenuId() { return menuId; }

    @Override
    public int compareTo(ToastMenuItem another) {
        return this.getPosition() - another.getPosition();
    }

    public void updateTextMap(int layoutId, String str) {

    }


    public static class Builder {
        private int menuId;
        private final int itemViewType;
        private int titleResId;
        private HashMap<Integer, Integer> imageMap;
        private HashMap<Integer, String> textMap;
        private HashMap<Integer, Integer> textIdMap;
        private boolean enabled = true;

        public Builder(int menuId, int itemViewType) {
            this.menuId = menuId;
            this.itemViewType = itemViewType;
            this.imageMap = new HashMap();
            this.textMap = new HashMap();
            this.textIdMap = new HashMap();
        }

        public Builder setMenuId(int menuId) {
            this.menuId = menuId;
            return this;
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

package com.twotoasters.toastnavmenu;

import java.util.HashMap;

/**
 * Created by patrickjackson on 7/29/14.
 */
public class ToastMenuItem implements Comparable<ToastMenuItem> {
    private final int menuId;
    private final int itemViewType;
    private int titleResId;
    private HashMap<Integer, Integer> imageMap;
    private HashMap<Integer, Integer> textMap;
    private boolean enabled;

    private ToastMenuItem(Builder builder) {
        menuId = builder.position;
        imageMap = builder.imageMap;
        textMap = builder.textMap;
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

    public HashMap getImageMap() {
        return imageMap;
    }

    public HashMap getTextMap() {
        return textMap;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public int getTextResourceForView(Integer viewId) {
        return textMap.get(viewId);
    }

    public int getImageResourceForView(Integer viewId) {
       return imageMap.get(viewId);
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
        private HashMap<Integer, Integer> textMap;
        private boolean enabled = true;

        public Builder(int position, int itemViewType) {
            this.position = position;
            this.itemViewType = itemViewType;
            this.imageMap = new HashMap();
            this.textMap = new HashMap();
        }

        public Builder addImage(int resourceId, int drawableId) {
            imageMap.put(resourceId, drawableId);
            return this;
        }

        public Builder addText(Integer resourceId, Integer textResourceId) {
            textMap.put(resourceId, textResourceId);
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

        public ToastMenuItem build() {
            return new ToastMenuItem(this);
        }
    }
}

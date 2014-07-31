package com.twotoasters.toastnavmenu;

import java.util.HashMap;

/**
 * Created by patrickjackson on 7/29/14.
 */
public class MenuItem {
    private final int menuId;
    private final int itemViewType;
    private int titleResId;
    private HashMap<Integer, Integer> imageMap;
    private HashMap<Integer, Integer> textMap;
    private boolean enabled;

    private MenuItem(Builder builder) {
        menuId = builder.menuId;
        imageMap = builder.imageMap;
        textMap = builder.textMap;
        titleResId = builder.titleResId;
        enabled = builder.enabled;
        itemViewType = builder.itemViewType;
    }

    public int getMenuId() {
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
    public static class Builder {
        private final int menuId;
        private final int itemViewType;
        private int titleResId;
        private HashMap<Integer, Integer> imageMap;
        private HashMap<Integer, Integer> textMap;
        private boolean enabled = true;

        public Builder(int menuId, int itemViewType) {
            this.menuId = menuId;
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

        public MenuItem build() {
            return new MenuItem(this);
        }
    }
}

package com.twotoasters.toastnavmenu;

import java.util.HashMap;

/**
 * Created by patrickjackson on 7/29/14.
 */
public class MenuItem {
    private final int menuId;
    private final int itemViewType;
    private int titleResId;
    private HashMap imageMap;
    private HashMap textMap;
    private boolean enabled;

    private MenuItem(Builder builder) {
        menuId = builder.menuId;
        imageMap = builder.imageMap;
        textMap = builder.textMap;
        titleResId = builder.titleResId;
        enabled = builder.enabled;
        itemViewType = builder.itemViewType;
    }


    public static class Builder {
        private final int menuId;
        private final int itemViewType;
        private int titleResId;
        private HashMap imageMap;
        private HashMap textMap;
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

        public Builder addText(int resourceId, int textResourceId) {
            imageMap.put(resourceId, textResourceId);
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

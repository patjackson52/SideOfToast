package com.twotoasters.toastnavmenu;

import android.content.Context;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by patrickjackson on 8/7/14.
 */
public class ToastMenuFooterItem extends ValueMappedItem
        implements Serializable {
    private final int layoutId;
    private int menuId;
    private final boolean enabled;

    private ToastMenuFooterItem(Builder builder) {
        super(builder.textMap, builder.imageMap);
        this.layoutId = builder.layoutId;
        this.enabled = builder.enabled;
        this.menuId = builder.menuId;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public int getMenuId() {
        return menuId;
    }

    public boolean isEnabled() {
        return enabled;
    }


    public static class Builder {
        private final int layoutId;
        private int menuId;
        private boolean enabled = true;
        private HashMap<Integer, Integer> imageMap;
        private HashMap<Integer, String> textMap;
        private HashMap<Integer, Integer> textIdMap;

        public Builder(int layoutId) {
            this.imageMap = new HashMap();
            this.textMap = new HashMap();
            this.textIdMap = new HashMap();
            this.layoutId = layoutId;
        }

        public Builder setMenuId(int menuId) {
            this.menuId = menuId;
            return this;
        }

        public ToastMenuFooterItem build(Context context) {
            for (Integer id : textIdMap.keySet()) {
                try {
                    textMap.put(id, context.getString(textIdMap.get(id)));
                } catch (Exception e) {
                    SideOfToast.log(context.getString(R.string.error_bad_string_map));
                }
            }
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

        public Builder addText(Integer resourceId, Integer textResourceId) {
            textIdMap.put(resourceId, textResourceId);
            return this;
        }

        public Builder addText(Integer resourceId, String string) {
            textMap.put(resourceId, string);
            return this;
        }
    }
}
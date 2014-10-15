package com.twotoasters.sideoftoast;

public class Events {

    public static class SetResourceEvent {
        private int menuId, layoutId, resourceId;
        private String str;

        public SetResourceEvent(int menuId, int layoutId, int resourceId) {
            this.layoutId = layoutId;
            this.resourceId = resourceId;
            this.menuId = menuId;
        }

        public SetResourceEvent(int menuId, int resId, String str) {
            this.menuId = menuId;
            this.layoutId = resId;
            this.str = str;
        }

        public int getMenuId() {
            return menuId;
        }

        public void setMenuId(int menuId) {
            this.menuId = menuId;
        }

        public int getLayoutId() {
            return layoutId;
        }

        public void setLayoutId(int layoutId) {
            this.layoutId = layoutId;
        }

        public int getResourceId() {
            return resourceId;
        }

        public void setResourceId(int resourceId) {
            this.resourceId = resourceId;
        }

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }
    }


    public static class ToastMenuItemClickEvent {
        final int position, menuId;

        public ToastMenuItemClickEvent(int position, int menuId) {
            this.position = position;
            this.menuId = menuId;
        }

        public int getPosition() {
            return position;
        }

        public int getMenuId() {
            return menuId;
        }
    }
}

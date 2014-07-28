package com.twotoasters.toastnavmenu;

import java.util.ArrayList;

/**
 * Created by patrickjackson on 7/27/14.
 */
public class Toaster {
    private final int listLayoutId;
    private final NavMenuItem items[];


    private Toaster(ToasterBuilder toasterBuilder) {
        items = (NavMenuItem[]) toasterBuilder.items.toArray();
        listLayoutId = toasterBuilder.listLayoutId;
    }

    public class ToasterBuilder {
        private final int listLayoutId;
        private ArrayList<NavMenuItem> items;


        public ToasterBuilder(int listLayoutId) {
            this.listLayoutId = listLayoutId;
            items = new ArrayList<>();
        }

        public ToasterBuilder item(NavMenuItem item) {
            items.add(item);
            return this;
        };

        public Toaster build() {
            return new Toaster(this);
        }
    }

}

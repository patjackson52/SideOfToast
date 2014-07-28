package com.twotoasters.toastnavmenu;

/**
 * Created by patrickjackson on 4/21/14.
 */
public final class NavMenuItem {

    public static final int SITTER_VIEW_SECTION_ID = 100;
    public static final int PARENT_VIEW_SECTION_ID = 100;
    public static final int HOME_ID = 101;
    public static final int MESSAGES_ID = 102;
    public static final int JOB_APPLICATIONS_ID = 103;
    public static final int APPLICATIONS_ID = 104;
    public static final int POST_JOB_ID = 105;
    public static final int PROFILE_ID = 106;
    public static final int LOGGED_OUT_SECTION_ID = 200;
    public static final int LOG_IN_ID = 201;
    public static final int JOIN_US_ID = 202;
    public static final int ARE_YOU_A_PARENT_SECTION_ID = 300;
    public static final int ARE_YOU_A_SITTER_SECTION_ID = 300;
    public static final int SWITCH_ROLES_ID = 301;

    public static final int ITEM_TYPE = 0;
    public static final int HEADER_TYPE = 1;

    public static NavMenuItem createHeader(int id, int label) {
        NavMenuItem item = new NavMenuItem();
        item.id = id;
        item.label = label;
        item.type = HEADER_TYPE;
        return item;
    }

    public static NavMenuItem createItem(int id, int label, int icon) {
        NavMenuItem item = new NavMenuItem();
        item.id = id;
        item.label = label;
        item.icon = icon;
        item.type = ITEM_TYPE;
        return item;
    }

    public NavMenuItem() {
    }

    public NavMenuItem(int type, int id, int label, int icon) {
        this.type = type;
        this.id = id;
        this.label = label;
        this.icon = icon;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    private int id;
    private int label;
    private int icon;
    int type;


}


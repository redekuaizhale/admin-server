package com.redekuaizhale.menu.constant;

public enum MenuConstant {

    /**
     * 父级菜单标识
     */
    PARENT_MENU_FLAG("0");


    String key;

    MenuConstant(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}

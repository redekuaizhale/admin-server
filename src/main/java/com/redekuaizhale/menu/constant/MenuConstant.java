package com.redekuaizhale.menu.constant;

/**
 * @author zhanghui
 * @date 2019-12-10
 * @company Dingxuan
 */
public enum MenuConstant {

    /**
     * 父级菜单标识
     */
    PARENT_MENU_FLAG("0");


    String value;

    MenuConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

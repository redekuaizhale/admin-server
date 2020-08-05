package com.zh.constants;

public enum RootConstant {

    /**
     * 根节点id
     */
    ROOT_ID("0");

    String value;

    RootConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

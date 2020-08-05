package com.zh.constants;

public enum StatusConstant {

    /**
     * 可用
     */
    IN_USE("可用"),

    /**
     * 禁用
     */
    NO_USE("禁用");

    String value;

    StatusConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

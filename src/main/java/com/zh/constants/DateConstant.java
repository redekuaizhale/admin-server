package com.zh.constants;

public enum DateConstant {

    YYYYMMDDHHMMSS("yyyyMMdd HHmmss"),

    YYYY_MM_DD("yyyy-MM-dd"),

    YYYYMMDD("yyyyMMdd"),

    YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss");

    String value;

    DateConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

package com.zh.constants;

public enum RoleConstant {

    /**
     * 超级管理员
     */
    ADMIN("超级管理员");

    String value;

    RoleConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

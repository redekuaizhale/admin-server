package com.redekuaizhale.constants;

public enum ResultConstant {

    /**
     * 成功
     */
    SUCCESS_CODE("1"),

    /**
     * 失败
     */
    ERROR_CODE("-1");

    String code;

    ResultConstant(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

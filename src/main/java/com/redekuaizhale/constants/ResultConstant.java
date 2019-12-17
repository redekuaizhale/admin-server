package com.redekuaizhale.constants;

/**
 * 返回结果常量
 * @author redekuaizhale
 * @date 2019-05-31
 * @company Dingxuan
 */
public enum ResultConstant {

    /**
     * 成功
     */
    SUCCESS_CODE("1"),

    /**
     * 失败
     */
    ERROR_CODE("-1");

    String key;

    ResultConstant(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}

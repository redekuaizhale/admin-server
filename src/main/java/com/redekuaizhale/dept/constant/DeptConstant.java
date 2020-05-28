package com.redekuaizhale.dept.constant;

/**
 * @author zhanghui
 * @date 2019-12-10
 * @company Dingxuan
 */
public enum DeptConstant {

    COMPANY_ENTITY("companyEntity");

    String value;

    DeptConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

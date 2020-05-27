package com.redekuaizhale.dept.constant;

public enum DeptConstant {

    COMPANY_ENTITY("companyEntity");

    String key;

    DeptConstant(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}

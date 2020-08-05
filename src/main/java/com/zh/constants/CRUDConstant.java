package com.zh.constants;

public enum CRUDConstant {

    /**
     * 新增
     */
    ADD("新增成功！"),

    /**
     * 删除
     */
    DELETE("删除成功！"),


    /**
     * 修改
     */
    UPDATE("修改成功！"),

    /**
     * 查询
     */
    QUERY("查询成功！");

    String value;

    CRUDConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}

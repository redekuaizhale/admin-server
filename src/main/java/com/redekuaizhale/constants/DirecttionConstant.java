package com.redekuaizhale.constants;

public enum DirecttionConstant {

    /**
     * 数字类型
     */
    INTEGER("Integer"),

    /**
     * 字符串类型
     */
    STRING("String"),

    /**
     * like 操作符
     */
    LIKE("Like"),

    /**
     * Between
     */
    BETWEEN("Between"),

    /**
     * StartWith
     */
    STARTWITH("StartWith"),

    /**
     * EndWith
     */
    ENDWITH("EndWith"),

    /**
     * Long
     */
    LONG("Long"),

    /**
     * Boolean
     */
    BOOLEAN("Boolean"),

    /**
     * Date
     */
    DATE("Date"),

    /**
     * BigDecimal
     */
    BIGDECIMAL("BigDecimal"),

    /**
     * List
     */
    LIST("List"),

    /**
     * DateTime
     */
    DATETIME("DateTime"),

    /**
     * 升序
     */
    ASC("asc"),

    /**
     * 降序
     */
    DESC("desc");

    /**
     * 编码
     */
    String code;

    DirecttionConstant(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

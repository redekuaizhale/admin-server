package com.zh.constants;

public enum MapConstant {

    INITIAL_CAPACITY(16);

    int value;

    MapConstant(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

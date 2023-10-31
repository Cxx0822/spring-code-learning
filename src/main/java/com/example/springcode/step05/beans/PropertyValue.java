package com.example.springcode.step05.beans;

/**
 * @Author: Cxx
 * @Date: 2023/10/31 22:23
 * @Description: Bean属性信息
 */
public class PropertyValue {
    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}

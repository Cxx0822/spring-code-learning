package com.example.springcode.beans;

/**
 * @Author: Cxx
 * @Date: 2023/10/31 22:23
 * @Description: Bean属性信息
 */
public class PropertyValue {
    /**
     * 属性名称 类型为字符串
     */
    private final String name;

    /**
     * 属性值 类型为Object 可以为任何值
     */
    private final Object value;

    /**
     * 构造函数
     * @param name  名称
     * @param value 值
     */
    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    /**
     * 获取名称
     * @return 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 获取值
     * @return 值
     */
    public Object getValue() {
        return value;
    }
}

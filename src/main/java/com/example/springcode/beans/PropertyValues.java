package com.example.springcode.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Cxx
 * @Date: 2023/10/31 22:26
 * @Description: Beans属性集合信息
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    /**
     * 增加属性信息
     * @param propertyValue 属性值
     */
    public void addPropertyValue(PropertyValue propertyValue) {
        this.propertyValueList.add(propertyValue);
    }

    /**
     * 获取属性值
     * @return 属性值
     */
    public PropertyValue[] getPropertyValues() {
        // 使用空数组将列表转为数组
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    /**
     * 根据属性名获取属性值
     * @param propertyName 属性名
     * @return 属性值
     */
    public PropertyValue getPropertyValue(String propertyName) {
        // 循环遍历 判断是否相等
        for (PropertyValue propertyValue : this.propertyValueList) {
            if (propertyValue.getName().equals(propertyName)) {
                return propertyValue;
            }
        }

        return null;
    }
}

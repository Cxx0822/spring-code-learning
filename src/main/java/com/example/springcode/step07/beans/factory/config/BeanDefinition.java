package com.example.springcode.step07.beans.factory.config;


import com.example.springcode.step07.beans.PropertyValues;

/**
 * Bean定义类
 *
 * @author Cxx
 */
public class BeanDefinition {
    /**
     * Bean类对象
     */
    private Class beanClass;

    /**
     * Bean 属性值
     */
    private PropertyValues propertyValues;

    /**
     * 构造函数
     *
     * @param beanClass bean类对象
     */
    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    /**
     * 构造函数
     *
     * @param beanClass      Bean类对象
     * @param propertyValues Bean属性值
     */
    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    /**
     * 获取Bean类对象
     *
     * @return Bean类对象
     */
    public Class getBeanClass() {
        return beanClass;
    }

    /**
     * 设置Bean类对象
     *
     * @param beanClass Bean类对象
     */
    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    /**
     * 获取Bean属性值
     *
     * @return Bean属性值
     */
    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    /**
     * 设置Bean属性值
     *
     * @param propertyValues Bean属性值
     */
    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}

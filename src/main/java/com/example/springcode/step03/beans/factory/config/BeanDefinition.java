package com.example.springcode.step03.beans.factory.config;

/**
 * Bean定义类
 * @author Cxx
 */
public class BeanDefinition {
    private Class<?> beanClass;

    /**
     * 构造函数 传入类对象
     * @param beanClass bean类对象
     */
    public BeanDefinition(Class<?> beanClass) {
        // 这里传入的是类对象 即 类.Class
        // 类对象包含了该类的所有信息（成员变量，方法，构造器等）
        // 通过该类对象也可以实例化类

        // 获取Class对象的方式主要有三种：
        // 1. 通过Class.forName(“类的全名称”)获取，用这个方法，最常见的应该是应用于JDBC注册驱动的时候用到的
        // 2. 通过已经实例化的对象获取，getClass()方法获取
        // 3. 通过类名.class获取

        this.beanClass = beanClass;
    }

    /**
     * 获取当前的类对象
     * @return 类对象
     */
    public Class<?> getBeanClass() {
        return beanClass;
    }

    /**
     * 设置当前的类对象
     * @param beanClass 类对象
     */
    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }
}

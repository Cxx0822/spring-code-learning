package com.example.springcode.beans.factory.config;


import com.example.springcode.beans.PropertyValues;

/**
 * Bean定义类
 * @author Cxx
 */
public class BeanDefinition {
    /**
     * 单例模式
     */
    String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;

    /**
     * 原型模式
     * 单例模式和原型模式的区别就在于是否存放到内存中，
     * 如果是原型模式那么就不会存放到内存中，每次获取都重新创建对象，
     * 另外非 Singleton 类型的 Bean 不需要执行销毁方法。
     */
    String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;

    /**
     * Bean类对象
     */
    private Class<?> beanClass;

    /**
     * Bean 属性值
     */
    private PropertyValues propertyValues;

    /**
     * 初始化方法名称
     */
    private String initMethodName;

    /**
     * 销毁方法名称
     */
    private String destroyMethodName;

    /**
     * 默认为单例模式
     */
    private String scope = SCOPE_SINGLETON;

    /**
     * 是否为单例模式
     */
    private boolean singleton = true;

    /**
     * 是否为原型模式
     */
    private boolean prototype = false;

    /**
     * 构造函数
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
        this.propertyValues = new PropertyValues();
    }

    /**
     * 构造函数
     * @param beanClass      Bean类对象
     * @param propertyValues Bean属性值
     */
    public BeanDefinition(Class<?> beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    /**
     * 获取当前的类对象
     * @return Bean类对象
     */
    public Class<?> getBeanClass() {
        return beanClass;
    }

    /**
     * 设置Bean类对象
     * @param beanClass Bean类对象
     */
    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    /**
     * 获取Bean属性值
     * @return Bean属性值
     */
    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    /**
     * 设置Bean属性值
     * @param propertyValues Bean属性值
     */
    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public String getInitMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String getDestroyMethodName() {
        return destroyMethodName;
    }

    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }

    public boolean isSingleton() {
        return singleton;
    }

    public void setSingleton(boolean singleton) {
        this.singleton = singleton;
    }

    public boolean isPrototype() {
        return prototype;
    }

    public void setPrototype(boolean prototype) {
        this.prototype = prototype;
    }
}

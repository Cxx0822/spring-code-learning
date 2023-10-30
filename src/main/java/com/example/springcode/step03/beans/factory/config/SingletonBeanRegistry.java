package com.example.springcode.step03.beans.factory.config;

/**
 * 单例Bean注册 接口
 */
public interface SingletonBeanRegistry {
    /**
     * 获取单例bean
     *
     * @param beanName bean名称
     * @return bean对象
     */
    Object getSingleton(String beanName);
}

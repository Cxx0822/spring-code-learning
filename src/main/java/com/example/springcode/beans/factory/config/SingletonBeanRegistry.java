package com.example.springcode.beans.factory.config;

/**
 * 单例Bean注册 接口
 *
 * @author Cxx
 */
public interface SingletonBeanRegistry {
    /**
     * 获取单例bean
     *
     * @param beanName bean名称
     * @return bean对象
     */
    Object getSingleton(String beanName);

    /**
     * 销毁单例对象
     */
    void destroySingletons();
}

package com.example.springcode.step03.beans.factory.support;

import com.example.springcode.step03.beans.factory.config.BeanDefinition;

/**
 * 注册Bean定义类
 */
public interface BeanDefinitionRegistry {

    /**
     * 注册Bean定义
     *
     * @param beanName       Bean名称
     * @param beanDefinition Bean定义
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}

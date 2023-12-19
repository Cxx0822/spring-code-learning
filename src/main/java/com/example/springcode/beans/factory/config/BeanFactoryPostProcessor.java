package com.example.springcode.beans.factory.config;

import com.example.springcode.beans.BeansException;
import com.example.springcode.beans.factory.ConfigurableListableBeanFactory;

/**
 * @Author: Cxx
 * @Date: 2023/11/11 21:56
 * @Description: 自定义修改BeanDefinition属性信息
 */
public interface BeanFactoryPostProcessor {
    /**
     * 在所有的BeanDefinition加载完成后 实例化Bean对象之前 提供修改BeanDefinition属性的机制
     * @param beanFactory Bean工厂
     * @throws BeansException Bean异常
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}

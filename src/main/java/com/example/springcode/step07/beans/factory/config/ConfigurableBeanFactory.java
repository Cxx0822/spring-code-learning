package com.example.springcode.step07.beans.factory.config;

import com.example.springcode.step07.beans.factory.HierarchicalBeanFactory;

/**
 * @Author: Cxx
 * @Date: 2023/11/14 22:54
 * @Description:
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}

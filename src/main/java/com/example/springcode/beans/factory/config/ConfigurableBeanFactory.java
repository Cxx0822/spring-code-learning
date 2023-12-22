package com.example.springcode.beans.factory.config;

import com.example.springcode.beans.factory.HierarchicalBeanFactory;

/**
 * @Author: Cxx
 * @Date: 2023/11/14 22:54
 * @Description:
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    /**
     * 增加Bean后置处理器
     * @param beanPostProcessor Bean后置处理器
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例对象
     */
    void destroySingletons();
}

package com.example.springcode.beans.factory;

import com.example.springcode.beans.factory.config.AutowireCapableBeanFactory;
import com.example.springcode.beans.factory.config.BeanDefinition;
import com.example.springcode.beans.factory.config.ConfigurableBeanFactory;
import com.example.springcode.beans.BeansException;

/**
 * @Author: Cxx
 * @Date: 2023/11/14 23:03
 * @Description:
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    /**
     * 根据Bean名称获取Bean定义
     * @param beanName Bean名称
     * @return 取Bean定义
     * @throws BeansException Bean异常
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 提前实例化单例Bean对象
     * @throws BeansException Bean异常
     */
    void preInstantiateSingletons() throws BeansException;
}

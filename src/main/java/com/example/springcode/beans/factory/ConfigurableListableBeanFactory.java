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
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;
}

package com.example.springcode.step07.beans.factory;

import com.example.springcode.step07.beans.BeansException;
import com.example.springcode.step07.beans.factory.config.AutowireCapableBeanFactory;
import com.example.springcode.step07.beans.factory.config.BeanDefinition;
import com.example.springcode.step07.beans.factory.config.ConfigurableBeanFactory;

/**
 * @Author: Cxx
 * @Date: 2023/11/14 23:03
 * @Description:
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws com.example.springcode.step06.beans.BeansException;
}

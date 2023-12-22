package com.example.springcode.test.common;

import com.example.springcode.beans.BeansException;
import com.example.springcode.beans.PropertyValue;
import com.example.springcode.beans.PropertyValues;
import com.example.springcode.beans.factory.ConfigurableListableBeanFactory;
import com.example.springcode.beans.factory.config.BeanDefinition;
import com.example.springcode.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @Author: Cxx
 * @Date: 12/19/23 7:01 PM
 * @Description:
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // 获取Bean定义
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");

        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company", "改为 estun"));
        System.out.println("执行 MyBeanFactoryPostProcessor");
    }
}

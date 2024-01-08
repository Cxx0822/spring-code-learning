package com.example.springcode.context.support;

import com.example.springcode.beans.BeansException;
import com.example.springcode.beans.factory.config.BeanPostProcessor;
import com.example.springcode.context.ApplicationContext;
import com.example.springcode.context.ApplicationContextAware;

/**
 * @Author: frank
 * @Date: 12/22/23 6:46 PM
 * @Description: 包装处理器
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {
    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}

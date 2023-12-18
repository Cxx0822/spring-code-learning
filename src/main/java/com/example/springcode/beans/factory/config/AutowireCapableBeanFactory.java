package com.example.springcode.beans.factory.config;

import com.example.springcode.beans.BeansException;
import com.example.springcode.beans.factory.BeanFactory;

/**
 * @Author: Cxx
 * @Date: 2023/11/14 23:05
 * @Description:
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
    /**
     * 执行BeanPostProcessors接口实现类的 postProcessBeforeInitialization方法
     * @param existingBean Bean对象
     * @param beanName     Bean名称
     * @return Bean对象
     * @throws BeansException Bean异常
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    /**
     * 执行BeanPostProcessors接口实现类的 postProcessAfterInitialization方法
     * @param existingBean Bean对象
     * @param beanName     Bean名称
     * @return Bean对象
     * @throws BeansException Bean异常
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;
}

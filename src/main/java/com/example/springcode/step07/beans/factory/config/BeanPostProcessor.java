package com.example.springcode.step07.beans.factory.config;

import com.example.springcode.step07.beans.BeansException;

/**
 * @Author: Cxx
 * @Date: 2023/11/14 22:56
 * @Description: 修改新实例化Bean对象
 */
public interface BeanPostProcessor {
    /**
     * 在Bean对象执行初始化方法之前 执行此方法
     * @param bean Bean对象
     * @param beanName Bean名称
     * @return Bean对象
     * @throws BeansException Bean异常
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 在Bean对象执行初始化方法之后 执行此方法
     * @param bean Bean对象
     * @param beanName Bean名称
     * @return Bean对象
     * @throws BeansException Bean异常
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}

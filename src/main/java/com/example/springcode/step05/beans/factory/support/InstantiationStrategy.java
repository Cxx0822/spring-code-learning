package com.example.springcode.step05.beans.factory.support;

import com.example.springcode.step05.beans.BeansException;
import com.example.springcode.step05.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 实例化类策略接口
 */
public interface InstantiationStrategy {
    /**
     * 实例化类
     *
     * @param beanDefinition Bean定义
     * @param beanName       Bean名称
     * @param ctor           类构造器 可以拿到参数类的构造函数
     * @param args           参数
     * @return 实例化的Bean
     * @throws BeansException Bean异常
     */
    Object instantiate(BeanDefinition beanDefinition,
                       String beanName,
                       Constructor ctor,
                       Object[] args) throws BeansException;
}

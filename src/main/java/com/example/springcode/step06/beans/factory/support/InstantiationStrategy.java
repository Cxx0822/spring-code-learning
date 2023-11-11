package com.example.springcode.step06.beans.factory.support;

import com.example.springcode.step06.beans.factory.config.BeanDefinition;
import com.example.springcode.step06.beans.BeansException;

import java.lang.reflect.Constructor;

/**
 * @Author: Cxx
 * @Date: 2023/11/11 15:25
 * @Description: 具体实例化创建Bean接口
 */
public interface InstantiationStrategy {
    /**
     * 实例化Bean类的方法 需要实现类实现
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

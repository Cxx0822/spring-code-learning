package com.example.springcode.step05.beans.factory.support;

import com.example.springcode.step05.beans.BeansException;
import com.example.springcode.step05.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 实例化创建Bean类的策略 基于反射的原理
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        // 通过Bean定义类获取Bean的Class信息
        Class clazz = beanDefinition.getBeanClass();

        try {
            // 有构造函数
            if (null != ctor) {
                // 通过反射实例化类
                // getDeclaredConstructor:返回类的构造函数
                return clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            }
            // 没有构造函数
            else {
                return clazz.getDeclaredConstructor().newInstance();
            }
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new BeansException("Failed to instantiate [" + clazz.getName() + "]", e);
        }
    }
}

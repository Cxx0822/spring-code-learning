package com.example.springcode.step06.beans.factory.support;

import com.example.springcode.step06.beans.BeansException;
import com.example.springcode.step06.beans.factory.config.BeanDefinition;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * @Author: Cxx
 * @Date: 2023/11/11 15:25
 * @Description: 基于动态代理的原理 实现创建Bean接口类
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        // 动态代理
        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(beanDefinition.getBeanClass());

        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });

        if (null == ctor) return enhancer.create();
        // 如果有构造函数 则传入参数
        return enhancer.create(ctor.getParameterTypes(), args);
    }
}

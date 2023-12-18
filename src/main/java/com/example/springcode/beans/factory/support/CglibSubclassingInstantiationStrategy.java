package com.example.springcode.beans.factory.support;

import com.example.springcode.beans.factory.config.BeanDefinition;
import com.example.springcode.beans.BeansException;
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
    public Object instantiate(BeanDefinition beanDefinition,
                              String beanName,
                              Constructor<?> ctor,
                              Object[] args) throws BeansException {
        // 动态代理
        Enhancer enhancer = new Enhancer();
        // 设置代码目标
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        // 设置单一回调对象，在调用中拦截对目标方法的调用
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });

        // 生成对象
        if (null == ctor) return enhancer.create();
        // 如果有构造函数 则传入参数
        return enhancer.create(ctor.getParameterTypes(), args);
    }
}

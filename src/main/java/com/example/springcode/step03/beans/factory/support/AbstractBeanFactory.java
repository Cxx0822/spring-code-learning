package com.example.springcode.step03.beans.factory.support;

import com.example.springcode.step03.beans.BeansException;
import com.example.springcode.step03.beans.factory.BeanFactory;
import com.example.springcode.step03.beans.factory.config.BeanDefinition;

/**
 * 抽象Bean工厂 继承单例Bean注册类 实现Bean工厂
 * @author Cxx
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    /**
     * 实现BeanFactory的获取Bean方法
     *
     * @param name Bean名称
     * @return Bean对象
     * @throws BeansException Bean异常
     */
    @Override
    public Object getBean(String name) throws BeansException {
        // 1. 从父类中获取Bean的单例
        Object bean = getSingleton(name);

        // 2. 如果获取到则返回Bean对象
        if (bean != null) {
            return bean;
        }

        // 3. 如果没有获取到 则获取Bean的定义并创建Bean
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition);
    }

    /**
     * 虚方法 获取Bean定义
     *
     * @param beanName Bean名称
     * @return Bean定义
     * @throws BeansException Bean异常
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 虚方法 创建Bean
     *
     * @param beanName       Bean名称
     * @param beanDefinition Bean定义
     * @return Bean对象
     * @throws BeansException Bean异常
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;
}

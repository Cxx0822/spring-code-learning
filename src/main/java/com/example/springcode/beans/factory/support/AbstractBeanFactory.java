package com.example.springcode.beans.factory.support;

import com.example.springcode.beans.factory.config.BeanDefinition;
import com.example.springcode.beans.BeansException;
import com.example.springcode.beans.factory.config.BeanPostProcessor;
import com.example.springcode.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Cxx
 * @Date: 2023/11/11 15:25
 * @Description: 抽象Bean工厂 继承单例Bean注册类 实现Bean工厂
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    /**
     * 实现BeanFactory的获取Bean方法
     * Bean在初始时并没有创建 只会在获取Bean时，如果没有获取到则进行创建
     * @param name Bean名称
     * @param args 参数
     * @return Bean对象
     */
    protected <T> T doGetBean(final String name, final Object[] args) {
        // 1. 从父类中获取Bean的单例
        Object bean = getSingleton(name);

        // 2. 如果获取到则返回Bean对象
        if (bean != null) {
            return (T) bean;
        }

        // 3. 如果没有获取到 则获取Bean的定义并创建Bean
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T) createBean(name, beanDefinition, args);
    }

    /**
     * 虚方法 获取Bean定义
     * @param beanName Bean名称
     * @return Bean定义
     * @throws BeansException Bean异常
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 虚方法 创建Bean 需要由子类去实现
     * @param beanName       Bean名称
     * @param beanDefinition Bean定义
     * @param args           其余参数
     * @return Bean对象
     * @throws BeansException Bean异常
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }
}

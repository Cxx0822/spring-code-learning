package com.example.springcode.step03.beans.factory.support;

import com.example.springcode.step03.beans.BeansException;
import com.example.springcode.step03.beans.factory.config.BeanDefinition;

/**
 * 继承抽象Bean工厂类
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    /**
     * 创建Bean对象
     *
     * @param beanName       Bean名称
     * @param beanDefinition Bean定义
     * @return Bean对象
     * @throws BeansException Bean异常
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean = null;

        try {
            // Bean的实例化
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException exception) {
            throw new BeansException("Instantiation of bean failed", exception);
        }

        // 添加到单例Bean中
        addSingleton(beanName, bean);
        return bean;
    }
}

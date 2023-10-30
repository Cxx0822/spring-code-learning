package com.example.springcode.step04.beans.factory.support;

import com.example.springcode.step04.beans.BeansException;
import com.example.springcode.step04.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 继承抽象Bean工厂类
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    /**
     * 实例化策略
     */
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    /**
     * 创建Bean对象
     *
     * @param beanName       Bean名称
     * @param beanDefinition Bean定义
     * @return Bean对象
     * @throws BeansException Bean异常
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;

        try {
            // Bean的实例化
            bean = createBeanInstance(beanDefinition, beanName, args);
        } catch (Exception exception) {
            throw new BeansException("Instantiation of bean failed", exception);
        }

        // 添加到单例Bean中
        addSingleton(beanName, bean);
        return bean;
    }

    /**
     * 创建Bean实例
     *
     * @param beanDefinition Bean定义
     * @param beanName       Bean名称
     * @param args           参数
     * @return Bean实例
     */
    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor constructorToUse = null;
        // 从Bean定义中获取Bean Class信息
        Class<?> beanClass = beanDefinition.getBeanClass();

        // 获取类的所有构造函数信息
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();

        for (Constructor ctor : declaredConstructors) {
            // 如果构造函数有参数
            if (null != args && ctor.getParameterTypes().length == args.length) {
                // 替换成该构造函数
                constructorToUse = ctor;
                break;
            }
        }

        // 实例化Bean类
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }
}

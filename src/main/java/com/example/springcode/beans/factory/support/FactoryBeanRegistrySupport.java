package com.example.springcode.beans.factory.support;

import com.example.springcode.beans.BeansException;
import com.example.springcode.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @Author: Cxx
 * @Date: 1/10/24 7:37 PM
 * @Description: FactoryBean 注册服务
 */
public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {
    /**
     * 存在单例对象Bean的缓存
     */
    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<String, Object>();

    protected Object getCacheObjectForFactoryBean(String beanName) {
        Object object = this.factoryBeanObjectCache.get(beanName);

        return (object != null ? object : null);
    }

    /**
     * 从工厂Bean中获取对象
     * @param factory  工厂
     * @param beanName Bean名称
     * @return 对象
     */
    protected Object getObjectFromFactoryBean(FactoryBean factory, String beanName) {
        // 如果是单例模式
        if (factory.isSingleton()) {
            // 先从缓存中获取
            Object object = this.factoryBeanObjectCache.get(beanName);

            if (object == null) {
                // 如果没有则从工厂中获取 并添加到缓存中
                object = doGetObjectFromFactoryBean(factory, beanName);
                this.factoryBeanObjectCache.put(beanName, (object != null ? object : null));
            }

            return (object != null ? object : null);
        } else {
            // 如果是原型模式 则直接从工厂中获取
            return doGetObjectFromFactoryBean(factory, beanName);
        }
    }

    /**
     * 从工厂Bean中获取对象
     * @param factory  工厂
     * @param beanName Bean名称
     * @return 对象
     */
    private Object doGetObjectFromFactoryBean(final FactoryBean factory, final String beanName) {
        try {
            return factory.getObject();
        } catch (Exception e) {
            throw new BeansException("FactoryBean threw exception on object[" + beanName + "] creation", e);
        }
    }
}

package com.example.springcode.step05.beans.factory.support;

import com.example.springcode.step05.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * 单例Bean注册的实现  实现单例Bean注册接口
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    /**
     * Bean存储
     */
    private Map<String, Object> singletonObjects = new HashMap<>();

    /**
     * 实现获取单例Bean对象的接口
     *
     * @param beanName Bean名称
     * @return Bean对象
     */
    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    /**
     * 注册单例Bean方法
     *
     * @param beanName        Bean名称
     * @param singletonObject Bean对象
     */
    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }
}

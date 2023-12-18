package com.example.springcode.beans.factory.support;

import com.example.springcode.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Cxx
 * @Date: 2023/11/11 15:25
 * @Description: 单例Bean注册实现类  实现单例Bean注册接口
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

    @Override
    public void destroySingletons() {

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

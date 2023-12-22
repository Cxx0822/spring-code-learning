package com.example.springcode.beans.factory.support;

import com.example.springcode.beans.BeansException;
import com.example.springcode.beans.factory.DisposableBean;
import com.example.springcode.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: Cxx
 * @Date: 2023/11/11 15:25
 * @Description: 单例Bean注册实现类  实现单例Bean注册接口
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    /**
     * Bean存储
     */
    private final Map<String, Object> singletonObjects = new HashMap<>();

    /**
     * 实现了DisposableBean接口的Bean
     */
    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    /**
     * 实现获取单例Bean对象的接口
     * @param beanName Bean名称
     * @return Bean对象
     */
    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    /**
     * 注册单例Bean方法
     * @param beanName        Bean名称
     * @param singletonObject Bean对象
     */
    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

    /**
     * 注册可销毁的Bean
     * @param beanName Bean名称
     * @param bean     Bean对象
     */
    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    /**
     * 销毁单例Bean
     */
    public void destroySingletons() {
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] disposableBeanNames = keySet.toArray();

        // 遍历可销毁的Bean
        for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeans.remove(beanName);

            try {
                // 执行销毁操作
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }
}

package com.example.springcode.step07.context.support;

import com.example.springcode.step07.beans.BeansException;
import com.example.springcode.step07.beans.factory.ConfigurableListableBeanFactory;
import com.example.springcode.step07.beans.factory.support.DefaultListableBeanFactory;

/**
 * @Author: Cxx
 * @Date: 2023/11/28 22:31
 * @Description: 获取Bean工厂和加载资源
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {
    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        //  实例化DefaultListableBeanFactory
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        // 加载资源配置
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return (ConfigurableListableBeanFactory) beanFactory;
    }
}

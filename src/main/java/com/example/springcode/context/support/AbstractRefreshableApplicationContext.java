package com.example.springcode.context.support;

import com.example.springcode.beans.factory.support.DefaultListableBeanFactory;
import com.example.springcode.beans.BeansException;
import com.example.springcode.beans.factory.ConfigurableListableBeanFactory;

/**
 * @Author: Cxx
 * @Date: 2023/11/28 22:31
 * @Description: 抽象可刷新应用上下文类
 * 实现了创建Bean工厂和加载Bean定义的抽象方法
 * 具体怎么加载Bean定义由继承类实现
 * 比如AbstractXmlApplicationContext继承类规定了如何通过解析xml文件获取Bean定义的功能
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {
    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        //  实例化DefaultListableBeanFactory
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        // 加载Bean定义
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    /**
     * 创建Bean工厂
     * @return Bean工厂
     */
    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    /**
     * 加载Bean定义
     * @param beanFactory Bean工厂
     */
    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}

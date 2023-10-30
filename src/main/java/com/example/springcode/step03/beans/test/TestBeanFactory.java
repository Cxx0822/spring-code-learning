package com.example.springcode.step03.beans.test;

import com.example.springcode.step03.beans.factory.config.BeanDefinition;
import com.example.springcode.step03.beans.factory.support.DefaultListableBeanFactory;
import com.example.springcode.step03.beans.service.DemoService;

public class TestBeanFactory {
    public void test() {
        // 1. 初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 实例化自定义Bean
        BeanDefinition beanDefinition = new BeanDefinition(DemoService.class);

        // 3. 将自定义的Bean注册到Bean工厂中
        beanFactory.registerBeanDefinition("demoService", beanDefinition);

        // 4.通过Bean工厂获取自定义的Bean
        DemoService demoService = (DemoService) beanFactory.getBean("demoService");

        // 5.调用自定义Bean的方法
        demoService.test();
    }
}

// 说明文档
// 1. 定义了一个Bean注册类DefaultSingletonBeanRegistry,并实现SingletonBeanRegistry接口
//    该类主要实现单例Bean的注册和获取功能
// 2. 定义了一个抽象基类AbstractBeanFactory,并继承DefaultSingletonBeanRegistry和实现BeanFactory接口
//    该类定义了获取bean的方法,并提供获取和创建Bean的抽象方法
// 3. 定义了一个抽象基类AbstractAutowireCapableBeanFactory,并继承AbstractBeanFactory
// 4. 定义了一个DefaultListableBeanFactoryBean工厂类,继承AbstractAutowireCapableBeanFactory,
//    并实现BeanDefinitionRegistry接口

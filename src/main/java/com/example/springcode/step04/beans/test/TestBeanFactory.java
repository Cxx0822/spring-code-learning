package com.example.springcode.step04.beans.test;

import com.example.springcode.step04.beans.factory.config.BeanDefinition;
import com.example.springcode.step04.beans.factory.support.DefaultListableBeanFactory;
import com.example.springcode.step04.beans.service.DemoService;

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
// 对比03的版本 主要增加了有参构造函数的Bean创建
// 1. 定义了一个根据bean名称实例化Bean类的策略接口InstantiationStrategy
// 2. 定义了两种具体实例化Bean类的策略类SimpleInstantiationStrategy和CglibSubclassingInstantiationStrategy
//    主要原理就是根据反射获取类的信息,并判断是否包含构造函数来实例化类
// 3. 更新了AbstractAutowireCapableBeanFactory类的创建Bean的方法

package com.example.springcode.step06.test;

import com.example.springcode.step06.beans.factory.xml.XmlBeanDefinitionReader;
import com.example.springcode.step06.beans.factory.support.DefaultListableBeanFactory;
import com.example.springcode.step06.test.bean.UserService;

/**
 * @Author: Cxx
 * @Date: 2023/11/11 17:08
 * @Description:
 */
public class TestBeanFactory06 {
    public void test() {
        // 1. 初始化Bean工厂
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件并注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 3. 获取Bean对象并调用方法
        UserService userService = beanFactory.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println(result);
    }
}

// 说明文档
// 对比05的版本 主要增加了通过配置文件加载Bean对象
// 主要通过XmlBeanDefinitionReader.cs 实现读取文件并解析文件内容 并将其注册到Bean定义中

package com.example.springcode.step05.beans.test;

import com.example.springcode.step05.beans.PropertyValue;
import com.example.springcode.step05.beans.PropertyValues;
import com.example.springcode.step05.beans.factory.config.BeanDefinition;
import com.example.springcode.step05.beans.factory.config.BeanReference;
import com.example.springcode.step05.beans.factory.support.DefaultListableBeanFactory;
import com.example.springcode.step05.beans.test.bean.UserDao;
import com.example.springcode.step05.beans.test.bean.UserService;

/**
 * @Author: Cxx
 * @Date: 2023/11/11 17:08
 * @Description:
 */
public class TestBeanFactory05 {
    public void test() {
        // 1. 初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 实例化自定义Bean
        BeanDefinition userDaoBeanDefinition = new BeanDefinition(UserDao.class);

        // 3. 将自定义的Bean注册到Bean工厂中
        beanFactory.registerBeanDefinition("userDao", userDaoBeanDefinition);

        // 4. 封装属性值
        PropertyValues propertyValues = new PropertyValues();
        // 增加普通属性
        propertyValues.addPropertyValue(new PropertyValue("id", "10001"));
        // 增加对象属性
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        // 5. 实例化自定义Bean
        BeanDefinition userServiceBeanDefinition  = new BeanDefinition(UserService.class, propertyValues);

        // 6. 将自定义的Bean注册到Bean工厂中
        beanFactory.registerBeanDefinition("userService", userServiceBeanDefinition);

        // 7. 获取UserService
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}

// 说明文档
// 对比04的版本 主要增加了为Bean对象注入属性的功能
// 1. 定义了一个属性值类PropertyValue和属性值列表类PropertyValues
// 2. 增加了AbstractAutowireCapableBeanFactory基类中创建Bean方法的填充属性功能
// 3. 增加了BeanDefinition定义类的带属性值的构造方法

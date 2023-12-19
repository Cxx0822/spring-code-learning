package com.example.springcode.test.common;

import com.example.springcode.beans.BeansException;
import com.example.springcode.beans.factory.config.BeanPostProcessor;
import com.example.springcode.test.bean.UserService;

/**
 * @Author: frank
 * @Date: 12/19/23 7:13 PM
 * @Description:
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setLocation("改为 Nanjing");
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}

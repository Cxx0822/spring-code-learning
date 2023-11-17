package com.example.springcode.step07.beans.factory.config;

/**
 * @Author: Cxx
 * @Date: 2023/10/31 23:12
 * @Description: Bean的引用
 */
public class BeanReference {
    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}

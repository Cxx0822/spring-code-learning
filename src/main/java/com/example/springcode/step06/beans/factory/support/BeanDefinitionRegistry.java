package com.example.springcode.step06.beans.factory.support;

import com.example.springcode.step06.beans.BeansException;
import com.example.springcode.step06.beans.factory.config.BeanDefinition;

/**
 * @Author: Cxx
 * @Date: 2023/11/11 15:25
 * @Description: 注册Bean接口
 */
public interface BeanDefinitionRegistry {

    /**
     * 注册Bean定义
     *
     * @param beanName       Bean名称
     * @param beanDefinition Bean定义
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 使用Bean名称查询Bean定义
     *
     * @param beanName Bean名称
     * @return Bean定义
     * @throws BeansException Bean异常
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 判断是否包含指定名称的Bean定义
     *
     * @param beanName Bean名称
     * @return 判断结果
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 获取注册表中所有的Bean名称
     *
     * @return 所有的Bean名称
     */
    String[] getBeanDefinitionName();
}

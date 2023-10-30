package com.example.springcode.step04.beans.factory.support;

import com.example.springcode.step04.beans.BeansException;
import com.example.springcode.step04.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {
    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    /**
     * 注册Bean定义
     *
     * @param beanName       Bean名称
     * @param beanDefinition Bean定义
     */
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    /**
     * 获取Bean定义
     *
     * @param beanName Bean名称
     * @return Bean定义
     * @throws BeansException Bean异常
     */
    @Override
    protected BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);

        if (beanDefinition == null) throw new BeansException("No bean named '" + beanName + "' is defined");
        return beanDefinition;
    }
}

package com.example.springcode.beans.factory.support;

import com.example.springcode.core.io.DefaultResourceLoader;
import com.example.springcode.core.io.ResourceLoader;

/**
 * @Author: Cxx
 * @Date: 2023/11/11 15:38
 * @Description: Bean定义读取抽象类
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
    /**
     * Bean注册
     */
    private final BeanDefinitionRegistry registry;

    /**
     * 加载Bean资源
     */
    private final ResourceLoader resourceLoader;

    /**
     * 构造函数  传入Bean注册类
     * @param registry Bean注册类
     */
    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    /**
     * 构造函数  传入Bean注册类和加载Bean资源类
     * @param registry       Bean注册类
     * @param resourceLoader 加载Bean资源类
     */
    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}

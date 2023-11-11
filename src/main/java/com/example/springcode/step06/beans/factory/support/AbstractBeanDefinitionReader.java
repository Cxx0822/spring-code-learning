package com.example.springcode.step06.beans.factory.support;

import com.example.springcode.step05.beans.factory.config.BeanDefinition;
import com.example.springcode.step06.beans.BeansException;
import com.example.springcode.step06.core.io.DefaultResourceLoader;
import com.example.springcode.step06.core.io.Resource;
import com.example.springcode.step06.core.io.ResourceLoader;

/**
 * @Author: Cxx
 * @Date: 2023/11/11 15:38
 * @Description: Bean定义读取抽象类
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
    private final BeanDefinitionRegistry registry;

    private final ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

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

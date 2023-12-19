package com.example.springcode.beans.factory.support;

import com.example.springcode.core.io.Resource;
import com.example.springcode.beans.BeansException;
import com.example.springcode.core.io.ResourceLoader;

/**
 * @Author: Cxx
 * @Date: 2023/11/11 15:25
 * @Description: Bean定义读取接口
 */
public interface BeanDefinitionReader {
    /**
     * 获取Bean注册
     * @return Bean注册
     */
    BeanDefinitionRegistry getRegistry();

    /**
     * 获取资源加载器
     * @return 资源加载器
     */
    ResourceLoader getResourceLoader();

    /**
     * 加载Bean定义的方法
     * @param resource 资源
     * @throws BeansException 异常
     */
    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String... location) throws BeansException;
}

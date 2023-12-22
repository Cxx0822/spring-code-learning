package com.example.springcode.context.support;


import com.example.springcode.beans.factory.config.BeanFactoryPostProcessor;
import com.example.springcode.beans.factory.config.BeanPostProcessor;
import com.example.springcode.beans.BeansException;
import com.example.springcode.beans.factory.ConfigurableListableBeanFactory;
import com.example.springcode.context.ConfigurableApplicationContext;
import com.example.springcode.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * @Author: Cxx
 * @Date: 2023/11/27 22:48
 * @Description: 抽象应用上下文 继承默认资源加载器类 实现可配置应用上下文接口
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {
    @Override
    public void refresh() throws BeansException {
        // 1. 创建BeanFactory 并加载BeanDefinition
        refreshBeanFactory();

        // 2. 获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 3. 在Bean实例化之前 执行Bean工厂后置处理器
        // 具体时间点: 拿到Bean工厂后 实例化Bean定义前
        // 具体功能: 可以更改Bean的一些属性
        // 拿到Bean工厂 就可以通过getBeanDefinition()拿到了Bean定义 也就拿到了Bean的属性和方法等
        invokeBeanFactoryPostProcessor(beanFactory);

        // 4. 注册Bean后置处理器 需要提前于其他Bean对象实例化之前执行注册操作
        // 这里只是注册 并不执行 执行在使用的过程中
        registerBeanPostProcessor(beanFactory);

        // 5. 提前实例化单例Bean对象  在这里实现实例化Bean的功能
        beanFactory.preInstantiateSingletons();
    }

    /**
     * 创建Bean工厂
     * @throws BeansException Bean异常
     */
    protected abstract void refreshBeanFactory() throws BeansException;

    /**
     * 获取Bean工厂 指定工厂类型为ConfigurableListableBeanFactory
     * @return Bean工厂
     */
    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    /**
     * 调用Bean工厂后置处理器
     * @param beanFactory Bean工厂
     */
    private void invokeBeanFactoryPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        // 找到所有的Bean工厂后置处理器
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    /**
     * 注册Bean后置处理器
     * @param beanFactory Bean工厂
     */
    private void registerBeanPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        // 找到所有的Bean后置处理器
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        getBeanFactory().destroySingletons();
    }
}

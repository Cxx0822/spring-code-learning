package com.example.springcode.step07.context.support;


import com.example.springcode.step07.beans.factory.support.BeanDefinitionRegistry;
import com.example.springcode.step07.beans.factory.support.DefaultListableBeanFactory;
import com.example.springcode.step07.beans.factory.xml.XmlBeanDefinitionReader;
import com.example.springcode.step07.core.io.ResourceLoader;

/**
 * @Author: Cxx
 * @Date: 2023/11/28 22:49
 * @Description: 上下文中对配置信息的加载
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader((BeanDefinitionRegistry) beanFactory, (ResourceLoader) this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}

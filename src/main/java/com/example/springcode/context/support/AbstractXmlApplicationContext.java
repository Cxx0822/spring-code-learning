package com.example.springcode.context.support;


import com.example.springcode.beans.factory.support.BeanDefinitionRegistry;
import com.example.springcode.beans.factory.support.DefaultListableBeanFactory;
import com.example.springcode.beans.factory.xml.XmlBeanDefinitionReader;
import com.example.springcode.core.io.ResourceLoader;

/**
 * @Author: Cxx
 * @Date: 2023/11/28 22:49
 * @Description: 抽象xml文件应用上下文类
 * 继承与抽象可刷新应用上下文类 主要实现通过XmlBeanDefinitionReader解析xml文件
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        // 实例化 xml解析Bean定义阅读器
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        // 获取所有的xml文件路径
        String[] configLocations = getConfigLocations();
        if (null != configLocations) {
            // 加载Bean定义
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    /**
     * 配置文件路径信息
     * @return 路径信息
     */
    protected abstract String[] getConfigLocations();
}

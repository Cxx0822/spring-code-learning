package com.example.springcode.context.support;

import com.example.springcode.beans.BeansException;

/**
 * @Author: Cxx
 * @Date: 2023/11/28 22:58
 * @Description: 解析xml文件应用上下文类
 * 具体实现类 继承于抽象xml文件应用上下文类 主要实现存储配置文件路径和初始化刷新Bean容器功能
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {
    /**
     * 配置文件路径
     */
    private String[] configLocations;

    public ClassPathXmlApplicationContext() {

    }

    /**
     * 从XML中加载BeanDefinition 并刷新上下文
     * @param configLocations 配置
     * @throws BeansException Bean异常
     */
    public ClassPathXmlApplicationContext(String configLocations) throws BeansException {
        // 字符串转为数组
        this(new String[]{configLocations});
    }

    public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException {
        // 存储配置文件路径
        this.configLocations = configLocations;
        // 刷新
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }
}

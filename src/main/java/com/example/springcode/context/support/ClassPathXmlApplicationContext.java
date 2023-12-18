package com.example.springcode.context.support;

import com.example.springcode.beans.BeansException;

/**
 * @Author: Cxx
 * @Date: 2023/11/28 22:58
 * @Description:
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {
    private String[] configLocations;

    public ClassPathXmlApplicationContext() {

    }

    /**
     * 从XML中加载BeanDefinition 并刷新上下文
     *
     * @param configLocations 配置
     * @throws BeansException Bean异常
     */
    public ClassPathXmlApplicationContext(String configLocations) throws BeansException {
        this(new String[]{configLocations});
    }

    public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException {
        this.configLocations = configLocations;
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }
}

package com.example.springcode.context;

import com.example.springcode.beans.BeansException;

/**
 * @Author: Cxx
 * @Date: 2023/11/27 22:49
 * @Description: 可配置的应用上下文接口
 */
public interface ConfigurableApplicationContext extends ApplicationContext {
    /**
     * 刷新容器
     * @throws BeansException Bean异常
     */
    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();
}

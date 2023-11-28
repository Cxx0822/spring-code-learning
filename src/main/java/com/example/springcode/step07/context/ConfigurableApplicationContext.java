package com.example.springcode.step07.context;

import com.example.springcode.step07.beans.BeansException;

/**
 * @Author: Cxx
 * @Date: 2023/11/27 22:49
 * @Description:
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

package com.example.springcode.step05.beans.factory;

import com.example.springcode.step05.beans.BeansException;

/**
 * Bean工厂
 */
public interface BeanFactory {
    /**
     * 获取Bean对象
     *
     * @param name Bean名称
     * @return Bean对象
     * @throws BeansException Bean异常
     */
    Object getBean(String name) throws BeansException;

    /**
     * 重载包含参数信息的获取Bean对象方法
     *
     * @param name Bean名称
     * @param args 参数
     * @return Bean对象
     * @throws BeansException Bean异常
     */
    Object getBean(String name, Object... args) throws BeansException;
}

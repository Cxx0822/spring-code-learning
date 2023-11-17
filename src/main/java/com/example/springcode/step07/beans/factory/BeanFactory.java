package com.example.springcode.step07.beans.factory;

import com.example.springcode.step07.beans.BeansException;

/**
 * @Author: Cxx
 * @Date: 2023/11/11 15:25
 * @Description: Bean工厂接口
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

    /**
     * 获取Bean对象
     *
     * @param name         Bean名称
     * @param requiredType 类型
     * @param <T>          类型
     * @return Bean对象
     * @throws BeansException Bean异常
     */
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;
}

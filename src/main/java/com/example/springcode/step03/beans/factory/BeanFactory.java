package com.example.springcode.step03.beans.factory;

import com.example.springcode.step03.beans.BeansException;

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
}

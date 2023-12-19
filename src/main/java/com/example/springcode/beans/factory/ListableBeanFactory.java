package com.example.springcode.beans.factory;


import com.example.springcode.beans.BeansException;

import java.util.Map;

/**
 * @Author: Cxx
 * @Date: 2023/11/14 22:41
 * @Description:
 */
public interface ListableBeanFactory extends BeanFactory {
    /**
     * 按照类型返回Bean实例
     * @param type 类型
     * @param <T>  泛型
     * @return Bean实例
     * @throws BeansException Beans异常
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 获取所有的Bean名称
     * @return Bean名称
     */
    String[] getBeanDefinitionNames();
}

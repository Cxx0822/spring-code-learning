package com.example.springcode.beans.factory;

import com.example.springcode.beans.BeansException;

/**
 * @Author: Cxx
 * @Date: 12/22/23 6:39 PM
 * @Description:
 */
public interface BeanFactoryAware extends Aware {
    /**
     * 设置Bean工厂 实现此接口 即能感知所属的Bean工厂
     * @param beanFactory Bean工厂
     * @throws BeansException Bean异常
     */
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}

package com.example.springcode.beans.factory;

/**
 * @Author: Cxx
 * @Date: 1/10/24 7:34 PM
 * @Description: 工厂Bean接口
 */
public interface FactoryBean<T> {
    /**
     * 获取对象
     * @return 对象
     * @throws Exception 异常
     */
    T getObject() throws Exception;

    /**
     * 获取对象类型
     * @return 对象类型
     */
    Class<?> getObjectType();

    /**
     * 是否是单例对象
     * @return 结果
     */
    boolean isSingleton();
}

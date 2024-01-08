package com.example.springcode.beans.factory;

/**
 * @Author: Cxx
 * @Date: 12/22/23 6:41 PM
 * @Description:
 */
public interface BeanClassLoaderAware extends Aware {
    /**
     * 设置Bean类加载器
     * 实现此接口即能感知所属的类加载器
     * @param classLoader 类加载器
     */
    void setBeanClassLoader(ClassLoader classLoader);
}

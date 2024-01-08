package com.example.springcode.beans.factory;

/**
 * @Author: Cxx
 * @Date: 12/22/23 6:43 PM
 * @Description:
 */
public interface BeanNameAware extends Aware {
    /**
     * 设置Bean名称
     * 实现此接口 即能感知所属的Bean名称
     * @param name Bean名称
     */
    void setBeanName(String name);
}

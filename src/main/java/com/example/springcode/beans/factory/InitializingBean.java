package com.example.springcode.beans.factory;

/**
 * @Author: Cxx
 * @Date: 12/20/23 6:48 PM
 * @Description: 初始化Bean接口
 */
public interface InitializingBean {
    /**
     * Bean处理了属性填充之后调用
     * @throws Exception 异常
     */
    void afterPropertiesSet() throws Exception;
}

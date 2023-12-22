package com.example.springcode.beans.factory;

/**
 * @Author: Cxx
 * @Date: 12/20/23 6:51 PM
 * @Description: 销毁Bean
 */
public interface DisposableBean {
    /**
     * 销毁Bean
     * @throws Exception 异常
     */
    void destroy() throws Exception;
}

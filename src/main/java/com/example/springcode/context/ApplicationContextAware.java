package com.example.springcode.context;

import com.example.springcode.beans.BeansException;
import com.example.springcode.beans.factory.Aware;

/**
 * @Author: Cxx
 * @Date: 12/22/23 6:45 PM
 * @Description:
 */
public interface ApplicationContextAware extends Aware {
    /**
     * 设置应用上下文
     * 实现此接口 即能感知所属的应用上下文
     * @param applicationContext 应用上下文
     * @throws BeansException Bean异常
     */
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}

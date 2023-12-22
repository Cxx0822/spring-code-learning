package com.example.springcode.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.example.springcode.beans.BeansException;
import com.example.springcode.beans.factory.DisposableBean;
import com.example.springcode.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @Author: frank
 * @Date: 12/20/23 7:07 PM
 * @Description: 销毁方法适配器
 */
public class DisposableBeanAdapter implements DisposableBean {
    private final Object bean;

    private final String beanName;

    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        // 如果bean对象实现了DisposableBean接口 则进行销毁操作
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }

        // 判断destroyMethodName是否存在
        if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);

            if (null == destroyMethod) {
                throw new BeansException("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
            }

            destroyMethod.invoke(bean);
        }
    }
}

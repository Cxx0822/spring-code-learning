package com.example.springcode.step07.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.example.springcode.step07.beans.factory.config.AutowireCapableBeanFactory;
import com.example.springcode.step07.beans.factory.config.BeanDefinition;
import com.example.springcode.step07.beans.BeansException;
import com.example.springcode.step07.beans.PropertyValue;
import com.example.springcode.step07.beans.PropertyValues;
import com.example.springcode.step07.beans.factory.config.BeanPostProcessor;
import com.example.springcode.step07.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;

/**
 * @Author: Cxx
 * @Date: 2023/11/11 15:25
 * @Description: 继承抽象Bean工厂类
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {
    /**
     * 具体实例化创建Bean对象的 实例化策略
     */
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    /**
     * 具体创建Bean对象的方法
     *
     * @param beanName       Bean名称
     * @param beanDefinition Bean定义
     * @return Bean对象
     * @throws BeansException Bean异常
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;

        try {
            // Bean的实例化
            bean = createBeanInstance(beanDefinition, beanName, args);
            // Bean填充属性
            applyPropertyValues(beanName, bean, beanDefinition);
        } catch (Exception exception) {
            throw new BeansException("Instantiation of bean failed", exception);
        }

        // 添加到单例Bean中
        addSingleton(beanName, bean);
        return bean;
    }

    /**
     * 创建Bean实例
     *
     * @param beanDefinition Bean定义
     * @param beanName       Bean名称
     * @param args           参数
     * @return Bean实例
     */
    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor constructorToUse = null;
        // 通过反射 从Bean定义中获取Bean Class信息
        Class<?> beanClass = beanDefinition.getBeanClass();

        // 获取Bean Class 所有构造函数信息
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();

        for (Constructor ctor : declaredConstructors) {
            // 如果构造函数有参数
            if (null != args && ctor.getParameterTypes().length == args.length) {
                // 替换成该构造函数
                constructorToUse = ctor;
                break;
            }
        }

        // 选用相应的策略 实例化Bean类
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    /**
     * 填充Bean对象属性值
     *
     * @param beanName       Bean名称
     * @param bean           Bean
     * @param beanDefinition Bean定义
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            // 拿到所有的属性值
            PropertyValues propertyValues = beanDefinition.getPropertyValues();

            // 遍历属性值 填充属性
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                // 判断该属性值是否是Bean对象
                if (value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    // 先拿到Bean对象名称 在拿到具体的Bean对象类
                    value = getBean(beanReference.getBeanName());
                }

                // 使用工具类 属性填充
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception exception) {
            throw new BeansException("Error setting property values: " + beanName);
        }
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    private Object initialBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        // TODO
        return null;
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }
}

package com.example.springcode.step05.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.example.springcode.step05.beans.BeansException;
import com.example.springcode.step05.beans.PropertyValue;
import com.example.springcode.step05.beans.PropertyValues;
import com.example.springcode.step05.beans.factory.config.BeanDefinition;
import com.example.springcode.step05.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;

/**
 * 继承抽象Bean工厂类
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    /**
     * 实例化策略
     */
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    /**
     * 创建Bean对象
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
        // 从Bean定义中获取Bean Class信息
        Class<?> beanClass = beanDefinition.getBeanClass();

        // 获取类的所有构造函数信息
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();

        for (Constructor ctor : declaredConstructors) {
            // 如果构造函数有参数
            if (null != args && ctor.getParameterTypes().length == args.length) {
                // 替换成该构造函数
                constructorToUse = ctor;
                break;
            }
        }

        // 实例化Bean类
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();

            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                // 是否是BeanReference类的实例
                if (value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }

                // 属性填充
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
}

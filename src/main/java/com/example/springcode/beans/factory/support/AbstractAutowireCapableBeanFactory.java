package com.example.springcode.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.example.springcode.beans.factory.DisposableBean;
import com.example.springcode.beans.factory.InitializingBean;
import com.example.springcode.beans.factory.config.AutowireCapableBeanFactory;
import com.example.springcode.beans.factory.config.BeanDefinition;
import com.example.springcode.beans.BeansException;
import com.example.springcode.beans.PropertyValue;
import com.example.springcode.beans.PropertyValues;
import com.example.springcode.beans.factory.config.BeanPostProcessor;
import com.example.springcode.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @Author: Cxx
 * @Date: 2023/11/11 15:25
 * @Description: 能够自动装配的Bean工厂 即可以对其进行写操作
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {
    /**
     * 具体实例化创建Bean对象的 实例化策略
     */
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    /**
     * 具体创建Bean对象的方法
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
            // 执行Bean初始化方法和BeanPostProcessor的前置和后置处理方法
            bean = initialBean(beanName, bean, beanDefinition);
        } catch (Exception exception) {
            throw new BeansException("Instantiation of bean failed", exception);
        }

        // 注册实现了DisposableBean接口的Bean对象
        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);

        // 添加到单例Bean中
        addSingleton(beanName, bean);
        return bean;
    }

    /**
     * 创建Bean实例
     * @param beanDefinition Bean定义
     * @param beanName       Bean名称
     * @param args           参数
     * @return Bean实例
     */
    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor<?> constructorToUse = null;
        // 通过反射 从Bean定义中获取Bean Class信息
        Class<?> beanClass = beanDefinition.getBeanClass();

        // 获取Bean Class 所有构造函数信息
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();

        for (Constructor<?> ctor : declaredConstructors) {
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

                // 判断该属性值是否是Bean对象 循环依赖的问题
                // 即当前Bean对象的属性也是个Bean对象
                // 属性填充时 会以BeanReference()对象的形式填充
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

    /**
     * 获取实例化Bean对象策略
     * @return 策略
     */
    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    /**
     * 设置实例化Bean对象策略
     * @param instantiationStrategy 策略
     */
    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    /**
     * 初始化Bean对象
     * @param beanName       Bean名称
     * @param bean           Bean对象
     * @param beanDefinition Bean定义
     * @return Bean对象
     */
    private Object initialBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 1. 执行BeanPostProcessor Before处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        try {
            // 执行初始化方法
            invokeInitMethods(beanName, wrappedBean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Invocation of init method of bean[" + beanName + "] failed", e);
        }

        // 2. 执行BeanProcessor After处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);

        return wrappedBean;
    }

    /**
     * 执行初始化函数
     * @param beanName       Bean名称
     * @param bean           Bean对象
     * @param beanDefinition Bean定义
     */
    private void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {
        // 如果bean对象实现了InitializingBean接口 则进行初始化操作
        if (bean instanceof InitializingBean) {
            ((InitializingBean) bean).afterPropertiesSet();
        }

        // 判断initMethodName是否存在
        String initMethodName = beanDefinition.getInitMethodName();
        if (StrUtil.isNotEmpty(initMethodName)) {
            // 通过反射拿到方法
            Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
            if (null == initMethod) {
                throw new BeansException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
            }

            initMethod.invoke(bean);
        }
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

    /**
     * 注册可销毁的Bean对象
     * @param beanName       Bean名称
     * @param bean           Bean对象
     * @param beanDefinition Bean定义
     */
    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
    }
}

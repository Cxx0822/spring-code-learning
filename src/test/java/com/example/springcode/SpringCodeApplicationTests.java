package com.example.springcode;

import com.example.springcode.beans.PropertyValue;
import com.example.springcode.beans.PropertyValues;
import com.example.springcode.beans.factory.config.BeanDefinition;
import com.example.springcode.beans.factory.config.BeanReference;
import com.example.springcode.beans.factory.support.DefaultListableBeanFactory;
import com.example.springcode.beans.factory.xml.XmlBeanDefinitionReader;
import com.example.springcode.context.support.ClassPathXmlApplicationContext;
import com.example.springcode.test.bean.UserDao;
import com.example.springcode.test.bean.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringCodeApplicationTests {
    @Test
    void test() {
        test07();
    }

    private void test04() {
        // 初始化Bean工厂
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 注册Bean定义
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 获取Bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }

    private void test05() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. UserDao 注册
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        // 3. UserService 设置属性[uId、userDao]
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("id", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        // 4. UserService 注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 5. UserService 获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }

    private void test06() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        UserService userService = beanFactory.getBean("userService", UserService.class);
        System.out.println(userService.queryUserInfo());
    }

    private void test07() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:springPostProcessor.xml");

        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println(userService.getCompany());
        System.out.println(userService.getLocation());
        System.out.println(userService.queryUserInfo());
    }
}



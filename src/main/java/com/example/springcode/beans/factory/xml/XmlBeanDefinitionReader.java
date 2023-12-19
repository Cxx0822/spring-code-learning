package com.example.springcode.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.example.springcode.beans.factory.config.BeanReference;
import com.example.springcode.beans.factory.support.AbstractBeanDefinitionReader;
import com.example.springcode.beans.factory.support.BeanDefinitionRegistry;
import com.example.springcode.beans.PropertyValue;
import com.example.springcode.beans.factory.config.BeanDefinition;
import com.example.springcode.core.io.Resource;
import com.example.springcode.beans.BeansException;
import com.example.springcode.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: Cxx
 * @Date: 2023/11/11 16:07
 * @Description: 通过解析XML读取Bean定义加载类
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try (InputStream inputStream = resource.getInputStream()) {
            doLoadBeanDefinitions(inputStream);
        } catch (IOException | ClassNotFoundException e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(String... locations) throws BeansException {
        for (String location: locations) {
            loadBeanDefinitions(location);
        }
    }

    /**
     * 真正执行 通过文件解析Bean定义 的类
     * @param inputStream 资源文件
     * @throws ClassNotFoundException 异常
     */
    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        Document doc = XmlUtil.readXML(inputStream);
        Element root = doc.getDocumentElement();
        NodeList childNodes = root.getChildNodes();

        // 遍历文件节点
        for (int i = 0; i < childNodes.getLength(); i++) {
            // 判断是否为元素
            if (!(childNodes.item(i) instanceof Element)) {
                continue;
            }

            // 判断是否为对象
            if (!"bean".equals(childNodes.item(i).getNodeName())) {
                continue;
            }

            // 解析标签
            Element bean = (Element) childNodes.item(i);
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");

            // 获取Class
            Class<?> clazz = Class.forName(className);
            // 优先获取id作为bean名称
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            // 定义Bean
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            // 读取属性并填充
            for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                if (!(bean.getChildNodes().item(j) instanceof Element)) {
                    continue;
                }

                if (!"property".equals(bean.getChildNodes().item(j).getNodeName())) {
                    continue;
                }

                // 解析标签
                Element property = (Element) bean.getChildNodes().item(j);
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");

                // 获取属性值
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                // 创建属性信息
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }

            // 防止重复注册
            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }

            // 注册Bean定义
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }
}

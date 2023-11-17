package com.example.springcode.step07.core.io;

/**
 * @Author: Cxx
 * @Date: 2023/11/11 15:02
 * @Description: 资源加载器接口
 */
public interface ResourceLoader {
    String CLASSPATH_URL_PREFIX = "classpath:";

    // 获取资源
    Resource getResource(String location);
}

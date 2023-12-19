package com.example.springcode.core.io;

/**
 * @Author: Cxx
 * @Date: 2023/11/11 15:02
 * @Description: 资源加载器接口
 */
public interface ResourceLoader {
    /**
     * 资源文件位置
     */
    String CLASSPATH_URL_PREFIX = "classpath:";

    /**
     * 获取资源
     *
     * @param location 资源位置
     * @return 资源
     */
    Resource getResource(String location);
}

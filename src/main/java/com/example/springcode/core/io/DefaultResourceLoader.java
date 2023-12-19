package com.example.springcode.core.io;

import org.springframework.util.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Author: Cxx
 * @Date: 2023/11/11 15:04
 * @Description: 资源加载器默认实现类
 */
public class DefaultResourceLoader implements ResourceLoader {
    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "Location must not be null");

        // 从ClassPath中加载
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        } else {
            try {
                // 从url中加载
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                // 从文件中加载
                return new FileSystemResource(location);
            }
        }
    }
}

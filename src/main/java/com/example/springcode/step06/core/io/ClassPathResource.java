package com.example.springcode.step06.core.io;

import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: Cxx
 * @Date: 2023/11/11 14:48
 * @Description: 读取ClassPath下的文件信息类
 */
public class ClassPathResource implements Resource {
    /**
     * 文件路径
     */
    private final String path;

    /**
     * 类加载器
     */
    private final ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, (ClassLoader) null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        // 路径不能为空
        Assert.notNull(path, "Path must not be null");
        this.path = path;
        // 初始化类加载器
        this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }

    @Override
    public InputStream getInputStream() throws IOException {
        // 利用类加载器获取文件的输入流
        InputStream is = classLoader.getResourceAsStream(path);

        if (is == null) {
            throw new FileNotFoundException(this.path + " cannot be opened because it does not exist");
        }

        return is;
    }
}

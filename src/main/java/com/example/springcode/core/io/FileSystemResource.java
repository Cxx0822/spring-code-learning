package com.example.springcode.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: Cxx
 * @Date: 2023/11/11 14:54
 * @Description: 通过指定文件路径的方式读取文件信息类
 */
public class FileSystemResource implements Resource {
    /**
     * 文件路径
     */
    private final String path;

    /**
     * 文件
     */
    private final File file;

    /**
     * 构造函数
     * @param file 文件
     */
    public FileSystemResource(File file) {
        this.file = file;
        // 通过文件获取文件路径
        this.path = file.getPath();
    }

    /**
     * 构造函数
     * @param path 文件路径
     */
    public FileSystemResource(String path) {
        // 通过文件路径创建文件
        this.file = new File(path);
        this.path = path;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    /**
     * 获取当前路径
     * @return 路径
     */
    public final String getPath() {
        return this.path;
    }
}

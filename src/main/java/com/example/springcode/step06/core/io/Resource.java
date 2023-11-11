package com.example.springcode.step06.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: Cxx
 * @Date: 2023/11/11 14:45
 * @Description: 资源加载接口
 */
public interface Resource {
    /**
     * 获取InputStream流的方法
     *
     * @return InputStream流
     * @throws IOException IO异常
     */
    InputStream getInputStream() throws IOException;
}

package com.example.springcode.io;

import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author: Cxx
 * @Date: 2023/11/11 14:58
 * @Description: 通过url读取文件类
 */
public class UrlResource implements Resource {
    /**
     * url路径
     */
    private final URL url;

    public UrlResource(URL url) {
        Assert.notNull(url, "URL must not be null");
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        // 打开url连接
        URLConnection con = this.url.openConnection();

        try {
            // 获取输入流
            return con.getInputStream();
        } catch (IOException ex) {
            // 如果是http连接 取消断开连接
            if (con instanceof HttpURLConnection) {
                ((HttpURLConnection) con).disconnect();
            }
            throw ex;
        }
    }
}

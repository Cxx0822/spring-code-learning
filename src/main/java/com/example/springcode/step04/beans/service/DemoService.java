package com.example.springcode.step04.beans.service;

/**
 * 测试类
 */
public class DemoService {
    private String name;

    /**
     * 无参构造函数
     */
    public DemoService() {

    }

    /**
     * 有参构造函数
     * @param name 名字
     */
    public DemoService(String name) {
        this.name = name;
    }

    public void test() {
        System.out.println("Hello World");
    }

    public void test_args() {
        System.out.println("Hello World " + this.name);
    }
}
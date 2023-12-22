package com.example.springcode.test.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Cxx
 * @Date: 2023/11/11 17:07
 * @Description:
 */
public class UserDao {
    private static final Map<String, String> hashMap = new HashMap<>();

    public void initDataMethod() {
        System.out.println("执行 initDataMethod");
        hashMap.put("10001", "Cxx");
    }

    public void destroyDataMethod() {
        System.out.println("执行 destroyDataMethod");
        hashMap.clear();
    }

    public String queryUserName(String id) {
        return hashMap.get(id);
    }
}

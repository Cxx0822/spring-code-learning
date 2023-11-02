package com.example.springcode.step05.beans.test.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Cxx
 * @Date: 2023/11/2 22:35
 * @Description: 用户Dao
 */
public class UserDao {
    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "Cxx1");
        hashMap.put("10002", "Cxx2");
        hashMap.put("10003", "Cxx3");
    }

    public String queryUserName(String id) {
        return hashMap.get(id);
    }
}

package com.example.springcode.test.bean;

import com.example.springcode.beans.factory.DisposableBean;
import com.example.springcode.beans.factory.InitializingBean;

/**
 * @Author: Cxx
 * @Date: 2023/11/11 17:08
 * @Description:
 */
public class UserService implements InitializingBean, DisposableBean {
    private String id;

    private String company;

    private String location;

    private UserDao userDao;

    public String queryUserInfo() {
        return userDao.queryUserName(id) + "," + company + "," + location;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行 destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行 afterPropertiesSet");
    }
}

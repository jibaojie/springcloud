package com.example.springcloud.provider.interceptor.service;

import com.example.springcloud.entity.mybatis.User;

import java.util.List;

/**
 * @author jbj
 * @create 2019-07-04 11:18
 */
public interface UserService {

    User getUser(int id);

    List<User> selectUser();

    int insert(User user);

    int update(User user);
}

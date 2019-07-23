package com.example.springcloud.common.oauth2server.dao;

import com.example.springclod.common.mapper.MyMapper;
import com.example.springcloud.entity.auth2.User;

/**
 * @author jbj
 * @create 2019-05-13 12:42
 */
public interface UserDao extends MyMapper<User> {
    User getUserByName(String username);
}

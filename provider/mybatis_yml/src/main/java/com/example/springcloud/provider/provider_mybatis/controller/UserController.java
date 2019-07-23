package com.example.springcloud.provider.provider_mybatis.controller;

import com.example.springcloud.entity.auth2.User;
import com.example.springcloud.provider.provider_mybatis.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jbj
 * @create 2019-07-01 14:55
 */
@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/getUser")
    public List<User> selectUser() {
        return userDao.selectAll();
    }


}

package com.example.springcloud.common.oauth2server.controller;

import com.example.springcloud.common.oauth2server.service.UserServiceImp;
import com.example.springcloud.entity.auth2.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jbj
 * @create 2019-05-13 14:09
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;

    @GetMapping("/list")
    public List<User> list() {
        return userServiceImp.getAll() ;
    }
}

package com.example.springcloud.provider.mybatis_conf2.controller;

import com.example.springcloud.entity.mybatis.User;
import com.example.springcloud.provider.mybatis_conf2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jbj
 * @create 2019-07-04 11:16
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUser")
    public User getUser(int id) {
        return userService.getUser(id);
    }

    @GetMapping("/selectUser")
    public List<User> selectUser() {
        return userService.selectUser();
    }

    @PostMapping("/insert")
    public int insert(@RequestBody User user) {
        return userService.insert(user);
    }

}

package com.example.springcloud.consumer.comsumer3.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jbj
 * @create 2019-05-21 16:54
 */
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @RequestMapping("getUser")
    public String getUser() {
        log.info("UserController -> getUser");
        return "getUser";
    }
}

package com.example.springcloud.consumer.comsumer3.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jbj
 * @create 2019-05-21 17:10
 */
@RestController
@RequestMapping("hello")
@Slf4j
public class HelloController {

    @RequestMapping("/hello")
    public void hello() {
        log.info("HelloController -> hello");
    }
}

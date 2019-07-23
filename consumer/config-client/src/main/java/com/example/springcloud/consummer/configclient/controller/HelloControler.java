package com.example.springcloud.consummer.configclient.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jbj
 * @create 2019-04-29 11:39
 */

@RestController
@Slf4j
public class HelloControler {

    @Value("${from}")
    private String from;

    @GetMapping("/from")
    public String from() {
        return from;
    }
}

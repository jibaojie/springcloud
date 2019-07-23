package com.example.springcloud.common.oauth2server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jbj
 * @create 2019-05-10 9:14
 */
@RestController
@RequestMapping("product")
public class ProductController {

    @GetMapping("list")
    public String list() {
        return "list";
    }

    @GetMapping("add")
    public String add() {
        return "add";
    }

    @GetMapping("del")
    public String del() {
        return "del";
    }

    @GetMapping("edit")
    public String edit() {
        return "edit";
    }


}

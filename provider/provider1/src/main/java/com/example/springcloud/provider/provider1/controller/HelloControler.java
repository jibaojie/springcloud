package com.example.springcloud.provider.provider1.controller;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Applications;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @author jbj
 * @create 2019-04-29 11:39
 */

@RestController
@Slf4j
public class HelloControler {

    @GetMapping("/hello")
    public String hello() {
//        try {
//            log.info("休眠5S");
//            Thread.sleep(20000);
//        } catch (InterruptedException e) {
//            log.error(e.getMessage());
//        }
        return "hello";
    }
}

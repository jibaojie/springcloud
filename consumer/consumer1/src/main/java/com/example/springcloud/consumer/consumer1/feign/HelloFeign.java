package com.example.springcloud.consumer.consumer1.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author jbj
 * @create 2019-04-29 17:07
 */
@FeignClient(name = "provider1-v1")
public interface HelloFeign {

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello();
}

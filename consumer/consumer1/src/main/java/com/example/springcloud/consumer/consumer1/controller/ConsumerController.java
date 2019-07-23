package com.example.springcloud.consumer.consumer1.controller;

import com.example.springcloud.consumer.consumer1.feign.HelloFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jbj
 * @create 2019-04-29 14:50
 */
@Slf4j
@RestController
public class ConsumerController {

    @Autowired
    private HelloFeign helloFeign;

    @Autowired
    private RestOperations restOperations;


    @GetMapping("testRestTemplate")
    public String testRestTemplate() {
        String content = restOperations.getForEntity("http://PROVIDER1/hello", String.class).getBody();
        return content;
    }


    @GetMapping("helloConsumer")
    public String helloConsumer() {
        long d1 = System.currentTimeMillis();
        String s = null;
        try {
            s = helloFeign.hello();
        } catch (Exception e) {
            log.info(e.getMessage(), e);
        }
        long d2 = System.currentTimeMillis();
        return "花费时间：" + (d2 - d1) / 1000 + s;
    }

    @GetMapping("indexUrl")
    public void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("index.html");
    }

}

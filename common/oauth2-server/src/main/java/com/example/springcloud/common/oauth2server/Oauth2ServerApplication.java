package com.example.springcloud.common.oauth2server;

import com.example.springclod.common.spring.SpringContextHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author 70934
 */
@SpringBootApplication
@MapperScan("com.example.springcloud.common.oauth2server")
public class Oauth2ServerApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Oauth2ServerApplication.class, args);
        //将bean注入context
        SpringContextHolder.setContext(context);
        System.out.println("注入成功");
    }

}

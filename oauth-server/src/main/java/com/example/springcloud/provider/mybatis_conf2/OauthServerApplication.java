package com.example.springcloud.provider.mybatis_conf2;

import com.example.springclod.common.spring.SpringContextHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.example.springcloud.oauthserver.dao")
public class OauthServerApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OauthServerApplication.class, args);
        SpringContextHolder.setContext(context);
    }

}

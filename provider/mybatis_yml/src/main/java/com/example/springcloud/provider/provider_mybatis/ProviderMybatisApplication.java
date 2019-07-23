package com.example.springcloud.provider.provider_mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.example.springcloud.provider.provider_mybatis.dao")
public class ProviderMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderMybatisApplication.class, args);
    }

}

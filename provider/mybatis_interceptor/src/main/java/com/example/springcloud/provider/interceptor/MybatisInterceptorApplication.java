package com.example.springcloud.provider.interceptor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author 70934
 * PageHelperAutoConfiguration在多数据源时发生错误
 */
@SpringBootApplication
@MapperScan("com.example.springcloud.provider.interceptor.dao")
public class MybatisInterceptorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisInterceptorApplication.class, args);
    }

}

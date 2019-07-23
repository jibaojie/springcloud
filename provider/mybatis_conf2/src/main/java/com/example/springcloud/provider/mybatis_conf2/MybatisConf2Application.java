package com.example.springcloud.provider.mybatis_conf2;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author 70934
 * PageHelperAutoConfiguration在多数据源时发生错误
 */
@SpringBootApplication(exclude = PageHelperAutoConfiguration.class)
@MapperScan("com.example.springcloud.provider.mybatis_conf2.dao")
public class MybatisConf2Application {

    public static void main(String[] args) {
        SpringApplication.run(MybatisConf2Application.class, args);
    }

}

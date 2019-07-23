package com.example.springcloud.provider.mybatis_conf2;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.springcloud.entity.mybatis.User;
import com.example.springcloud.provider.mybatis_conf2.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisConf2ApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void testRead() {
        System.out.println(userService.getUser(1).toString());
        System.out.println(userService.selectUser().toString());
    }

    @Test
    public void testUpdate() {
        User user = userService.getUser(1);
        user.setId(2);
        userService.insert(user);
        System.out.println(userService.selectUser().toString());
    }

    @Test
    public void testTransaction() {
    }

}

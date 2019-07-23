package com.example.springcloud.provider.interceptor;

import com.example.springcloud.entity.mybatis.User;
import com.example.springcloud.provider.interceptor.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisInterceptorApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void testUpdate() {
        userService.selectAll();

        userService.selectAll();
    }

}

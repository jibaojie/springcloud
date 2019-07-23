package com.example.springcloud.provider.interceptor;

import com.example.springcloud.entity.mybatis.DataSourceEntity;
import com.example.springcloud.entity.mybatis.User;
import com.example.springcloud.provider.interceptor.conf.DBContextHolder;
import com.example.springcloud.provider.interceptor.conf.DynamicDatasource;
import com.example.springcloud.provider.interceptor.service.DataSourceEntityService;
import com.example.springcloud.provider.interceptor.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisConf3ApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private DataSourceEntityService dataSourceEntityService;

    @Test
    public void testUpdate() {
        User user = userService.getUser(1);
        System.out.println(user.toString());
    }

    @Test
    public void testDataSource() {
        User user = userService.getUser(1);
        System.out.println(user.toString());

        DynamicDatasource dynimaticDataSource = new DynamicDatasource();
        dynimaticDataSource.addDataSource("192.168.3.129","192.168.3.129", 3306, "test", "root", "123456");
        DBContextHolder.setDbKey("192.168.3.129");
        User user2 = userService.getUser(1);
        System.out.println(user2.toString());

        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                DBContextHolder.setDbKey("192.168.3.129");
            } else {
                DBContextHolder.setDbKey("defaultDS");
            }
            System.out.println(userService.getUser(1).toString());
        }
    }

    @Test
    public void testInsertData() {
        DataSourceEntity dataSourceEntity = new DataSourceEntity("192.168.3.129","192.168.3.129", 3306, "test", "root", "123456");

        System.out.println(dataSourceEntityService.insert(dataSourceEntity));
    }

}

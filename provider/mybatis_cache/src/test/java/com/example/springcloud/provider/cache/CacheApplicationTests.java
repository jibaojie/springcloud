package com.example.springcloud.provider.cache;

import com.example.springcloud.entity.mybatis.User;
import com.example.springcloud.provider.cache.dao.UserDao;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheApplicationTests {

    @Autowired
    private UserDao userDao;

    /**
     * 一级缓存测试，会得到不同的对象
     */
    @Test
    public void testCache1() {
        User u1 = userDao.selectByPrimaryKey(1);
        User u2 = userDao.selectByPrimaryKey(1);
        Assert.isTrue(u1 == u2, "不相等");
    }

    /**
     * 一级缓存测试，使用事务时会查询一次数据库，得到一个对象
     */
    @Test
    @Transactional
    public void testCache1Tr() {
        User u1 = userDao.selectByPrimaryKey(1);
        User u2 = userDao.selectByPrimaryKey(1);
        Assert.isTrue(u1 == u2, "不相等");
    }

    /**
     * 二级缓存测试，mapper添加注解
     * 查询一次数据库，得到一个对象
     * readWrite=false表示只读，返回同一个对象
     * readWrite=true表示读写，经过序列化返回两个个对象，实体必须序列化
     */
    @Test
    public void testCache2() {
        User u3 = userDao.getUser2(1);
        User u4 = userDao.getUser2(1);
        System.out.println(u3 == u4);
    }



}

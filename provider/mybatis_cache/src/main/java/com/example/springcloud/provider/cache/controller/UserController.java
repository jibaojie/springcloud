package com.example.springcloud.provider.cache.controller;

import com.example.springcloud.entity.mybatis.User;
import com.example.springcloud.provider.cache.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jbj
 * @create 2019-07-07 17:26
 */
@RequestMapping("/user")
@RestController
@Api("用户")
public class UserController extends BaseController<User, UserService> {

    /**
     * 测试浏览一级缓存：
     *      在同一个sqlSession中，重复查询同一个sql，会得到两个相同的对象
     *      一级缓存默认开启
     * @param id
     * @return
     */
    @Override
    @ApiOperation(value = "查询")
    public User getById(@RequestParam Object id) {
        Integer userId = Integer.valueOf((String) id) ;
        User user = biz.getById(userId);
        return user;
    }

}

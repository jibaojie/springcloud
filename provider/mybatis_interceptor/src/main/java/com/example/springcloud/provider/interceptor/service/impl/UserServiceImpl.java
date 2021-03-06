package com.example.springcloud.provider.interceptor.service.impl;

import com.example.springclod.common.base.BaseServiceImpl;
import com.example.springcloud.entity.mybatis.User;
import com.example.springcloud.provider.interceptor.dao.UserDao;
import com.example.springcloud.provider.interceptor.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author jbj
 * @create 2019-07-04 11:20
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<UserDao, User> implements UserService {


}

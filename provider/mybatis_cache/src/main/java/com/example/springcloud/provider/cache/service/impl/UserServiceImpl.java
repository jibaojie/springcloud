package com.example.springcloud.provider.cache.service.impl;

import com.example.springclod.common.base.BaseServiceImpl;
import com.example.springcloud.entity.mybatis.User;
import com.example.springcloud.provider.cache.dao.UserDao;
import com.example.springcloud.provider.cache.service.UserService;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.sql.Connection;

/**
 * @author jbj
 * @create 2019-07-04 11:20
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<UserDao, User> implements UserService {


}

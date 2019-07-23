package com.example.springcloud.provider.mybatis_conf2.service;

import com.example.springcloud.entity.auth2.User;
import com.example.springcloud.provider.mybatis_conf2.dao.UserDao;
import com.example.springcloud.provider.mybatis_conf2.entity.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author jbj
 * @create 2019-05-13 13:01
 */
@Service
@Slf4j
public class UserServiceImp {

    @Autowired
    private UserDao userDao;

    public LoginUser getUserByName(String username) {
        User user = new User();
        user.setUserName(username);
        List<User> userList =  userDao.select(user);
        if (userList.isEmpty()) {
            return null;
        }
        LoginUser loginUser = new LoginUser();
        try {
            PropertyUtils.copyProperties(loginUser, userList.get(0));
            loginUser.setUsername(username);
        }  catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.error(e.getMessage(), e);
        }
        return loginUser;
    }

}

package com.example.springcloud.common.oauth2server.service;

import com.example.springcloud.common.oauth2server.dao.UserDao;
import com.example.springcloud.common.oauth2server.entity.LoginUser;
import com.example.springcloud.entity.auth2.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author jbj
 * @create 2019-05-13 13:01
 */
@Service("userServiceImp")
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

    public List<User> getAll() {
        return userDao.selectAll();
    }
}

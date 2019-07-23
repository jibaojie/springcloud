package com.example.springcloud.common.oauth2server.config;

import com.example.springclod.common.spring.SpringContextHolder;
import com.example.springcloud.common.oauth2server.entity.LoginUser;
import com.example.springcloud.common.oauth2server.service.UserServiceImp;
import com.example.springcloud.entity.auth2.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Max;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author jbj
 * @create 2019-05-13 13:10
 *
 */
@Configuration
public class MyUserDetailService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUser user = SpringContextHolder.getBean(UserServiceImp.class).getUserByName(username);
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        if (user != null) {

        }
        return user;
    }

}

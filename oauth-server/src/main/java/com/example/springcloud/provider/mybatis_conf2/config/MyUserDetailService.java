package com.example.springcloud.provider.mybatis_conf2.config;

import com.example.springclod.common.spring.SpringContextHolder;
import com.example.springcloud.provider.mybatis_conf2.entity.LoginUser;
import com.example.springcloud.provider.mybatis_conf2.service.UserServiceImp;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
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

package com.example.springcloud.common.oauth2server.config;

import com.example.springcloud.common.oauth2server.filter.ImageCodeAuthenticationFilter;
import com.mysql.cj.protocol.AuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author jbj
 * @create 2019-05-10 9:02
 *
 * @EnableWebSecurity: 启动SpringSecurity过滤器链
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * 配置认证管理器，提供认证方式
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new MyUserDetailService());
//        auth.inMemoryAuthentication().withUser("123").password("123").authorities("PRODUCT_ADD","PRODUCT_UPDATE");
    }


    /**
     * 配置拦截资源，定义跳转页面
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/product").permitAll()

                //登录页面不拦截
                .antMatchers("/login.html").permitAll()
                .antMatchers("/imageCode").permitAll()
                .antMatchers("/js/**").permitAll()
                //其他全部拦截
                .anyRequest().authenticated()
//                .antMatchers("/**").fullyAuthenticated()
                //无权限时跳转登录页面/login.html， loginProcessingUrl为前端登录请求接口login，由spring security负责验证
                .and().formLogin().loginPage("/login.html").loginProcessingUrl("/login")
                //登录成功处理类，返回json数据
                .successHandler(new MyAuthenticationSuccessHandler())
                //登录失败处理类，返回json数据
                .failureHandler(new MyAuthenticationFailureHandler())
                //登录成功之后跳转页面
//                .defaultSuccessUrl("/home.html").permitAll()
                //httpBasic弹出登录框
//                .and().httpBasic()
                //禁用csrf机制
                .and().csrf().disable()
        ;
        //添加拦截器
        http.addFilterBefore(new ImageCodeAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}

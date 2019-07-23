package com.example.springcloud.common.oauth2server.filter;

import com.example.springclod.common.util.http.CookieUtils;
import com.example.springcloud.common.oauth2server.exception.ImageCodeException;
import com.example.springcloud.common.oauth2server.util.ImageCodeCaheMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jbj
 * @create 2019-05-10 16:01
 * 自定义拦截器，在登录拦截器之前进行验证码拦截
 */
@Configuration
@Slf4j
public class ImageCodeAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            //判断当前请求为登录接口
            if (httpServletRequest.getRequestURI().contains("/login") && !httpServletRequest.getRequestURI().contains("/login.html")) {
                //获取验证码
                String imageCode = httpServletRequest.getParameter("imageCode");
                if (StringUtils.isEmpty(imageCode)) {
                    //抛出异常错误
                    throw new ImageCodeException("没有验证码");
                }
                String cookieValue = CookieUtils.getCookieValueIfNullThenSetCookie(httpServletRequest, httpServletResponse);

                if (!imageCode.equalsIgnoreCase(ImageCodeCaheMap.getImageCode(cookieValue).toLowerCase())) {
                    //抛出异常错误
                    throw new ImageCodeException("验证码错误");
                }
            }
        } catch (AuthenticationException e) {
            logger.error("验证码验证发生异常：" + e.getMessage());
            authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}

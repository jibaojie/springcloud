package com.example.springcloud.provider.mybatis_conf2.config;

import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jbj
 * @create 2019-05-10 14:59
 * desc 登录验证失败处理， 返回json数据
 */
@Configuration
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        Map<String, Object> resultMap = new HashMap<>(2);
        resultMap.put("success", false);
        resultMap.put("msg", "登录失败:" + e.getMessage() );

        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write( JSON.toJSONString(resultMap));
    }
}

package com.example.springcloud.common.oauth2server.config;

import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jbj
 * @create 2019-05-10 14:51
 * desc: 处理登录成功后的类， 返回json数据
 */
@Configuration
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        Map<String, Object> resultMap = new HashMap<>(2);
        resultMap.put("success", true);
        resultMap.put("msg", "登录成功");

        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write( JSON.toJSONString(resultMap));
    }
}

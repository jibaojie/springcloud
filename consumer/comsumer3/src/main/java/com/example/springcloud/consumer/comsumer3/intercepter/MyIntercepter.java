package com.example.springcloud.consumer.comsumer3.intercepter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author jbj
 * @create 2019-05-21 17:16
 */
@Configuration
public class MyIntercepter extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserIntercepter()).addPathPatterns("/user/**");
    }
}

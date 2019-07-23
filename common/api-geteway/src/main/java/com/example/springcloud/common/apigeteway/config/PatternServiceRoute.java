package com.example.springcloud.common.apigeteway.config;

import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jbj
 * @create 2019-05-06 13:17
 * @desc 自动为路由添加版本信息
 */
@Configuration
public class PatternServiceRoute {

    @Bean
    public PatternServiceRouteMapper serviceRouteMapper() {
        return new PatternServiceRouteMapper("(?<name>^.+)-(?<version>v.+$)", "${version}/${name}");
    }
}

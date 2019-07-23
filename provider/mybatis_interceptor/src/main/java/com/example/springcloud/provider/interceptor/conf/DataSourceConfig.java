package com.example.springcloud.provider.interceptor.conf;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jbj
 * @create 2019-07-04 10:29
 */
@Configuration
public class DataSourceConfig {

    @Bean
    public DruidDataSource druidDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

}

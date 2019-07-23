package com.example.springcloud.provider.interceptor.conf;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.example.springcloud.provider.interceptor.constant.DataSourceConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author jbj
 * @create 2019-07-04 10:29
 */
@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.master")
    public DruidDataSource masterDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    public DataSource dynamicDatasource(DruidDataSource masterDataSource) {
        //设置数据源为主数据源
        DBContextHolder.setDbKey(DataSourceConstant.DEFAULT_DS);
        //对AbstractRoutingDataSource设置默认数据源和数据源集合
        DynamicDatasource myRoutingDataSource = new DynamicDatasource();
        myRoutingDataSource.setDefaultTargetDataSource(masterDataSource);
        DynamicDatasource.addDataSourceMap(DataSourceConstant.DEFAULT_DS, masterDataSource);
        return myRoutingDataSource;
    }

}

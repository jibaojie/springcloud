package com.example.springcloud.provider.mybatis_conf2.conf;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.example.springcloud.provider.mybatis_conf2.enm.DBTypeEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jbj
 * @create 2019-07-04 10:29
 */
@Configuration
public class DataSourceConfig {

//    @Bean
//    @Primary
//    @ConfigurationProperties("spring.datasource.master")
//    public DataSource masterDataSource() {
//        return DataSourceBuilder.create().build();
//    }

//    @Bean
//    @ConfigurationProperties("spring.datasource.slave")
//    public DataSource slaveDataSource() {
//        return DataSourceBuilder.create().build();
//    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.master")
    public DruidDataSource masterDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.slave")
    public DruidDataSource slaveDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    public DataSource myRoutingDataSource(@Qualifier("masterDataSource") DruidDataSource masterDataSource,
                                          @Qualifier("slaveDataSource") DruidDataSource slave1DataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>(2);
        targetDataSources.put(DBTypeEnum.MASTER, masterDataSource);
        targetDataSources.put(DBTypeEnum.SLAVE, slave1DataSource);
        //对AbstractRoutingDataSource设置默认数据源和数据源集合
        MyRoutingDataSource myRoutingDataSource = new MyRoutingDataSource();
        myRoutingDataSource.setDefaultTargetDataSource(masterDataSource);
        myRoutingDataSource.setTargetDataSources(targetDataSources);
        return myRoutingDataSource;
    }

}

package com.example.springcloud.provider.mybatis_conf.conf;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

/**
 * @author jbj
 * @create 2019-07-01 17:40
 */
@Configuration
@MapperScan(basePackages = "com.example.springcloud.provider.mybatis_conf.dao.consumer", sqlSessionTemplateRef = "consumerSqlSessionTemplate")
public class ConsumerDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource2")
    public DataSource consumerDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactory consumerSqlSessionFactory(@Qualifier("consumerDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/consumer/*.xml"));
        return bean.getObject();
    }

    @Bean
    public DataSourceTransactionManager consumerTransactionManager(@Qualifier("consumerDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionTemplate consumerSqlSessionTemplate(@Qualifier("consumerSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}

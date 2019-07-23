package com.example.springcloud.provider.interceptor.conf;

import com.example.springcloud.entity.mybatis.DataSourceEntity;
import com.example.springcloud.provider.interceptor.service.DataSourceEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author jbj
 * @create 2019-07-06 16:28
 * 初始化数据源
 */
@Slf4j
@Configuration
public class InitDataSourceRunner implements CommandLineRunner {

    @Autowired
    private DataSourceEntityService dataSourceEntityService;

    @Override
    public void run(String... args) throws Exception {
        List<DataSourceEntity> list = dataSourceEntityService.selectAll();
        log.info("初始化数据源开始。。。");
        for (DataSourceEntity dataSourceEntity: list) {
            DataSource dataSource = DynamicDatasource.createDataSource(dataSourceEntity);
            DynamicDatasource.addDataSourceMap(dataSourceEntity.getKeyName(), dataSource);
        }
        log.info("初始化数据源结束。。。");
    }

}

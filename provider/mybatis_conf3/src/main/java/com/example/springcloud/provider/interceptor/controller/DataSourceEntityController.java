package com.example.springcloud.provider.interceptor.controller;

import com.example.springcloud.entity.mybatis.DataSourceEntity;
import com.example.springcloud.provider.interceptor.conf.DynamicDatasource;
import com.example.springcloud.provider.interceptor.service.DataSourceEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author jbj
 * @create 2019-07-06 17:19
 */
@RestController
@RequestMapping("/datasource")
public class DataSourceEntityController {

    @Autowired
    private DataSourceEntityService dataSourceEntityService;

    /**
     * 创建数据源放到内存中，并持久化
     * @param dataSourceEntity
     * @return
     */
    @PostMapping("insert")
    public int insert(@RequestBody DataSourceEntity dataSourceEntity) {

        DataSource dataSource = DynamicDatasource.createDataSource(dataSourceEntity);
        //存储数据源集合
        DynamicDatasource.addDataSourceMap(dataSourceEntity.getKeyName(), dataSource);

        return dataSourceEntityService.insert(dataSourceEntity);
    }

    @GetMapping("selectList")
    public List<DataSourceEntity> selectList() {
        return dataSourceEntityService.selectAll();
    }

    @GetMapping("getById")
    public DataSourceEntity getById(int id) {
        return dataSourceEntityService.getById(id);
    }


}

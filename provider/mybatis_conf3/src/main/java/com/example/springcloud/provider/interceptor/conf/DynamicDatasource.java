package com.example.springcloud.provider.interceptor.conf;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.springcloud.entity.mybatis.DataSourceEntity;
import com.sun.istack.internal.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jbj
 * @create 2019-07-04 10:56
 */
@Slf4j
public class DynamicDatasource extends AbstractRoutingDataSource {

    private static ConcurrentHashMap<String, DataSource> dataSourceMap = new ConcurrentHashMap<>(8);

    public static void addDataSourceMap(String key, DataSource dataSource) {
        dataSourceMap.put(key, dataSource);
    }

    /**
     * 检查是否包含指定id的数据源
     */
    public static boolean checkDbKey(String dbKey){
        return dataSourceMap.containsKey(dbKey);
    }

    @Nullable
    @Override
    protected String determineCurrentLookupKey() {
        logger.info("切换数据源到：" + DBContextHolder.getDbKey());
        return DBContextHolder.getDbKey();
    }

    /**
     * 对数据源的初始化方法，由于这里已经将数据源集合放在本类中，如果不重写将会由于父类参数为null而抛出异常。
     */
    @Override
    public void afterPropertiesSet() {}

    /**
     * 确定使用哪一个数据源
     * 这里不做null判断，因为是经过null判断后再进入的。
     */
    @Override
    protected DataSource determineTargetDataSource() {
        String dsKey = determineCurrentLookupKey();
        DataSource dds = dataSourceMap.get(dsKey);
        return dds;
    }

    /**
     * 添加数据源
     * 为了防止多线程添加同一个数据源，这里采用同步,同时会判断是否已存在
     * @param dbkey
     * @param ip
     * @param port
     * @param dbName 实例名
     * @param username
     * @param password
     * @return String 新建数据源对应的key，如果已经存在，则返回之前的key。
     */
    public synchronized String addDataSource(String dbkey, String ip,
                                             int port, String dbName, String username, String password){
        DataSourceEntity dataSourceEntity = new DataSourceEntity(ip, port, dbName, username, password);
        DataSource ds = createDataSource(dataSourceEntity);
        //存储数据源集合
        dataSourceMap.put(dbkey, ds);

        return dbkey;
    }

    /**
     * 创建一个数据源
     * @param dataSourceEntity
     * @return
     */
    public static DataSource createDataSource(DataSourceEntity dataSourceEntity){
        DruidDataSource dds = new DruidDataSource();
        dds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dds.setUrl("jdbc:mysql://" + dataSourceEntity.getIp()  + ":" + dataSourceEntity.getPort() + "/" + dataSourceEntity.getInstanceName());
        dds.setUsername(dataSourceEntity.getUsername());
        dds.setPassword(dataSourceEntity.getPassword());
        return dds;
    }

}

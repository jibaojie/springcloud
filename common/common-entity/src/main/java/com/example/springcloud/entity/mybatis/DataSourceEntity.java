package com.example.springcloud.entity.mybatis;

import com.example.springcloud.entity.base.BaseEntity;
import lombok.Data;
import tk.mybatis.mapper.annotation.NameStyle;

import javax.persistence.Table;

/**
 * @author jbj
 * @create 2019-07-06 8:27
 */
@Data
@Table(name = "data_source")
@NameStyle
public class DataSourceEntity extends BaseEntity {

    public DataSourceEntity(String ip, int port, String instanceName, String username, String password) {
        this.ip = ip;
        this.port = port;
        this.instanceName = instanceName;
        this.username = username;
        this.password = password;
    }

    public DataSourceEntity(String dbKey, String ip, int port, String instanceName, String username, String password) {
        this.keyName = dbKey;
        this.ip = ip;
        this.port = port;
        this.instanceName = instanceName;
        this.username = username;
        this.password = password;
    }

    /**
     * 标识当前数据源字段
     */
    private String keyName;
    /**
     * IP地址
     */
    private String ip;

    /**
     * 端口
     */
    private int port;

    /**
     * 默认数据库实例
     */
    private String instanceName;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

}

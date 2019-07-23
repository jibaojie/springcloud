package com.example.springcloud.entity.base;

import lombok.Data;

import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @author jbj
 * @create 2019-07-07 10:58
 */
@Data
public class BaseEntity implements Serializable {

    /**
     * 数据库标识，根据这个字段的值进行数据源切换，如果没有则使用默认数据源
     */
    @Transient
    private String dbKey;
    
}

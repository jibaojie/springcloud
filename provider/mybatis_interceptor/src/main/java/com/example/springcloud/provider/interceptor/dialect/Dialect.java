package com.example.springcloud.provider.interceptor.dialect;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.session.RowBounds;

/**
 * @author jbj
 * @create 2019-07-23 14:44
 * @description 数据库方言
 */
public interface Dialect {

    /**
     * 跳过count和分页查询
     * @param msId 执行的mybatis方法全名
     * @param parameterObject 方法参数
     * @param rowBounds 分页参数
     * @return true跳过，默认查询结果；false执行分页查询
     */
    boolean skip(String msId, Object parameterObject, RowBounds rowBounds);

    /**
     * 执行分页前，返回true进行count查询，返回false会继续进行下面的beforeCount
     * @param msId
     * @param parameterObject
     * @param rowBounds
     * @return
     */
    boolean  beforeCount(String msId, Object parameterObject, RowBounds rowBounds);

    /**
     * 生成count查询sql
     * @param boundSql 绑定sql对象
     * @param parameterObject
     * @param rowBounds
     * @param countKey
     * @return
     */
    String getCountSql(BoundSql boundSql, Object parameterObject, RowBounds rowBounds, CacheKey countKey);
}

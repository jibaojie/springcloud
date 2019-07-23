package com.example.springclod.common.base;

import java.util.List;
import java.util.Map;

/**
 * @author jbj
 * @create 2019-07-06 16:03
 */
public interface BaseServie<T> {

    /**
     * 新增
     * @param t
     * @return
     */
    int insert(T t);

    /**
     * 修改
     * @param t
     * @return
     */
    int update(T t);

    /**
     * 删除
     * @param t
     * @return
     */
    int delete(T t);

    /**
     * 查询列表
     * @param paramMap
     * @return
     */
    List<T> selectList(Map<String, Object> paramMap);

    /**
     * 查询所有
     * @return
     */
    List<T> selectAll();

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    T getById(String id);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    T getById(Integer id);
}

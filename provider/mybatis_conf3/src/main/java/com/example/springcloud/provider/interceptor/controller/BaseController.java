package com.example.springcloud.provider.interceptor.controller;

import com.example.springclod.common.base.BaseServie;
import com.example.springcloud.entity.base.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author jbj
 * @create 2019-07-07 11:27
 */
public class BaseController<T extends BaseEntity, Biz extends BaseServie<T>> {

    @Autowired
    private Biz biz;

    @PostMapping("insert")
    public T insert(@RequestBody T t) {
        biz.insert(t);
        return t;
    }

    @PostMapping("update")
    public T update(@RequestBody T t) {
        biz.update(t);
        return t;
    }

    @PostMapping("delete")
    public int delete(@RequestBody T t) {
        return biz.delete(t);
    }

    @GetMapping("select")
    public List<T> select(@RequestParam Map<String, Object> paramMap) {
        return biz.selectList(paramMap);
    }

    @GetMapping("getById")
    public T getById(@RequestParam Object id) {
        if (id instanceof String) {
            return biz.getById((String) id);
        } else {
            return biz.getById((Integer) id);
        }
    }

}

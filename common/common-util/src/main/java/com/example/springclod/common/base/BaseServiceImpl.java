package com.example.springclod.common.base;

import com.example.springclod.common.mapper.MyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * @author jbj
 * @create 2019-07-06 16:03
 */
public class BaseServiceImpl<M extends MyMapper, T> implements BaseServie<T> {

    @Autowired
    protected M mapper;


    @Override
    public int insert(T t) {
        return mapper.insert(t);
    }

    @Override
    public int update(T t) {
        return mapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int delete(T t) {
        return mapper.delete(t);
    }

    /**
     * TODO
     * @param paramMap
     * @return
     */
    @Override
    public List<T> selectList(Map<String, Object> paramMap) {
        return mapper.selectByExample(null);
    }

    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    /**
     * TODO
     * @param id
     * @return
     */
    @Override
    public T getById(String id) {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        Example example = new Example(clazz);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
//        criteria.andEqualTo("isDel", 0);
        List<T> list = mapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public T getById(Integer id) {
       return getById(id.toString());
    }
}

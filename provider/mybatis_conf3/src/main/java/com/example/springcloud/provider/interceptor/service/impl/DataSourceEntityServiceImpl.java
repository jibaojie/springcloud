package com.example.springcloud.provider.interceptor.service.impl;

import com.example.springclod.common.base.BaseServiceImpl;
import com.example.springcloud.entity.mybatis.DataSourceEntity;
import com.example.springcloud.provider.interceptor.dao.DataSourceEntityDao;
import com.example.springcloud.provider.interceptor.service.DataSourceEntityService;
import org.springframework.stereotype.Service;

/**
 * @author jbj
 * @create 2019-07-06 16:00
 */
@Service
public class DataSourceEntityServiceImpl extends BaseServiceImpl<DataSourceEntityDao, DataSourceEntity> implements DataSourceEntityService {


}

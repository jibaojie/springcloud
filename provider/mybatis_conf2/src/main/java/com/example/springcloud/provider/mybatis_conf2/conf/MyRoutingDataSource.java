package com.example.springcloud.provider.mybatis_conf2.conf;

import com.sun.istack.internal.Nullable;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author jbj
 * @create 2019-07-04 10:56
 */
public class MyRoutingDataSource extends AbstractRoutingDataSource {

    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        return DBContextHolder.get();
    }

}

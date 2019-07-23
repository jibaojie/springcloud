package com.example.springcloud.provider.interceptor.conf;

import com.example.springcloud.provider.interceptor.constant.DataSourceConstant;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jbj
 * @create 2019-07-04 10:49
 */
@Slf4j
public class DBContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    static String getDbKey() {
        return contextHolder.get();
    }

    public static void setDbKey(String dbKey) {
        if(DynamicDatasource.checkDbKey(dbKey)){
            contextHolder.set(dbKey);
        } else {
            //不存在数据源时使用默认数据源
            contextHolder.set(DataSourceConstant.DEFAULT_DS);
        }
    }

    public static void clear() {
        contextHolder.remove();
    }

}

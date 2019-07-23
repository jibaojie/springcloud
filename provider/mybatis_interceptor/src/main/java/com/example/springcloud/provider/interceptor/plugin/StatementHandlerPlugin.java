package com.example.springcloud.provider.interceptor.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.RowBounds;
import sun.plugin2.main.server.ResultHandler;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author jbj
 * @create 2019-07-23 10:06
 * @description 自定义mybatis插件
 * 注解要拦截哪个接口的哪个方法，可确定唯一方法。
 * 可设置多个拦截方法
 * type：拦截的接口
 * method：拦截的方法
 * args：方法参数
 * type、method、args可确定一个方法
 */

@Intercepts({
        /*
         * StatementHandler接口可拦截方法：
         *      prepare：在数据库被执行前调用，有限元当前接口中的其他方法被执行
         *      parameterize：方法在prepare之后被执行，用于处理参数信息
         *      batch：在全局配置defaultExecutorType="BATCH"时，数据库才会调用该方法
         *      query：执行select方法时被调用
         *      queryCursor：只会在返回值类型为Cursor<T>的查询中被调用
         */
        @Signature(
                type = StatementHandler.class,
                method = "prepare",
                args = {Connection.class, Integer.class}),
        @Signature(
                type = StatementHandler.class,
                method = "parameterize",
                args = {Statement.class}),
        @Signature(
                type = StatementHandler.class,
                method = "batch",
                args = {Statement.class}),
        @Signature(
                type = StatementHandler.class,
                method = "query",
                args = {Statement.class, ResultHandler.class}),
        @Signature(
                type = StatementHandler.class,
                method = "queryCursor",
                args = {Statement.class})
})
public class StatementHandlerPlugin implements Interceptor {

    /**
     * 用来传递插件的参数，可通过plugin标签在property标签中添加参数，在拦截器初始化时可通过此方法传递给拦截器
     *
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {

    }

    /**
     * 在创建被拦截的接口实现类时调用
     *
     * @param target 拦截器要拦截的对象
     * @return
     */
    @Override
    public Object plugin(Object target) {
        /*
         * 通过java的动态代理拦截目标对象
         * 该方法会自动判断拦截器的签名和被拦截对象的接口是否匹配，只有匹配的才会拦截目标对象
         */
        return Plugin.wrap(target, this);
    }

    /**
     * 当方法被拦截后要执行的逻辑
     *
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //目标对象
        Object target = invocation.getTarget();
        //拦截的方法
        Method method = invocation.getMethod();
        //被拦截方法中的参数
        Object[] args = invocation.getArgs();
        //执行下一个拦截器
        return invocation.proceed();
    }
}

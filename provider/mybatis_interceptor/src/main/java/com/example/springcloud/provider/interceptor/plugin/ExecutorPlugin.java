package com.example.springcloud.provider.interceptor.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.RowBounds;
import sun.plugin2.main.server.ResultHandler;

import java.lang.reflect.Method;
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
         * Executor接口可拦截方法：
         *      update：在所有insert、update、delete被执行时调用
         *      query：在select被执行时调用,最常被拦截的一个方法
         *      queryCursor：只有在查询类型的返回值为Cursor时被调用
         *      flushStatements：通过SQLSession调用flushStatements方法或者执行的接口中油@Flush注解时被调用
         *      commit：sqlSession调用commit方法时被调用
         *      getTransaction：sqlSession方法获取熟即可连接是被调用
         *      close：在延迟加载获取新的Executor后才会被执行
         *      isClosed：在延迟加载执行查询方法前被执行
         */
        @Signature(
                type = Executor.class,
                method = "update",
                args = {MappedStatement.class, Object.class}),
        @Signature(
                type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(
                type = Executor.class,
                method = "queryCursor",
                args = {MappedStatement.class, Object.class, RowBounds.class}),
        @Signature(
                type = Executor.class,
                method = "flushStatements",
                args = {}),
        @Signature(
                type = Executor.class,
                method = "commit",
                args = {boolean.class}),
        @Signature(
                type = Executor.class,
                method = "getTransaction",
                args = {}),
        @Signature(
                type = Executor.class,
                method = "close",
                args = {boolean.class}),
        @Signature(
                type = Executor.class,
                method = "isClosed",
                args = {boolean.class})
})
public class ExecutorPlugin implements Interceptor {

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

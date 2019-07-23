package com.example.springcloud.provider.interceptor.aop;

import com.example.springcloud.entity.base.BaseEntity;
import com.example.springcloud.provider.interceptor.conf.DBContextHolder;
import com.example.springcloud.provider.interceptor.constant.DataSourceConstant;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jbj
 * @create 2019-07-04 10:58
 * 定义切点，所有controller方法找到dbKey，切换数据源
 */
@Aspect
@Component
public class DataSourceAop {

    @Pointcut("execution(* com.example.springcloud.provider.interceptor.controller.*Controller.*(..))")
    public void pointcut() {}

    @Before("pointcut()")
    public void read(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();

        if (args.length > 0) {
            // 接收到请求，记录请求内容
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            String httpMethod = request.getMethod();
            if ("get".equalsIgnoreCase(httpMethod)) {
                //规定接口必须传dbKey
                String dbKey = request.getParameter(DataSourceConstant.DB_KEY);
                DBContextHolder.setDbKey(dbKey);
            } else {
                //放到body里面数据参数
                if (args[0] instanceof BaseEntity) {
                    //规定post请求第一个参数为实体，实体传DBKey
                    BaseEntity entity = (BaseEntity) args[0];
                    DBContextHolder.setDbKey(entity.getDbKey());
                }
            }
        } else {
            //没有带DBKey则使用默认数据源
            DBContextHolder.setDbKey(DataSourceConstant.DEFAULT_DS);
        }

    }


}

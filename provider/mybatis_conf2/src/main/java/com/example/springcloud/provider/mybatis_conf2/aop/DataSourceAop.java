package com.example.springcloud.provider.mybatis_conf2.aop;

import com.example.springcloud.provider.mybatis_conf2.conf.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author jbj
 * @create 2019-07-04 10:58
 * 定义两个切点，查询相关使用slave数据源增删改使用master数据源
 */
@Aspect
@Component
public class DataSourceAop {

    @Pointcut("(execution(* com.example.springcloud.provider.mybatis_conf2.service.*Service.select*(..)) " +
            "|| execution(* com.example.springcloud.provider.mybatis_conf2.service.*Service.get*(..)))")
    public void readPointcut() {}

    @Pointcut("@annotation(com.example.springcloud.provider.mybatis_conf2.anno.Master) " +
            "|| execution(* com.example.springcloud.provider.mybatis_conf2.service.*Service.insert*(..)) " +
            "|| execution(* com.example.springcloud.provider.mybatis_conf2.service.*Service.add*(..)) " +
            "|| execution(* com.example.springcloud.provider.mybatis_conf2.service.*Service.update*(..)) " +
            "|| execution(* com.example.springcloud.provider.mybatis_conf2.service.*Service.edit*(..)) " +
            "|| execution(* com.example.springcloud.provider.mybatis_conf2.service.*Service.delete*(..)) " +
            "|| execution(* com.example.springcloud.provider.mybatis_conf2.service.*Service.remove*(..))")
    public void writePointcut() {}

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }

}

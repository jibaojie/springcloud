package com.example.springclod.common.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

/**
 * @author jbj
 * @create 2019-05-13 15:23
 */
@Slf4j
public class SpringContextHolder {

    private static ApplicationContext context;

    public static <T> T getBean(String beanName) {
        return (T) getContext().getBean(beanName);
    }

    public static <T> T getBean(Class<T> clazz) {
        return getContext().getBean(clazz);
    }

    public static ApplicationContext getContext(){
        if (context == null) {
            log.error("SpringContextHoder属性未注入");
        }
        return context;
    }

    /**
     * 项目运行完成后，将bean放入ApplicationContext中
     * @param context
     */
    public static void setContext(ApplicationContext context) {
        SpringContextHolder.context = context;
    }
}

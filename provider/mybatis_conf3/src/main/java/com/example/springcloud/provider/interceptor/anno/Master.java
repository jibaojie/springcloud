package com.example.springcloud.provider.interceptor.anno;

import java.lang.annotation.*;

/**
 * @author jbj
 * @create 2019-07-04 11:30
 * 自定义注解，有@Master注解的方法强制读主库
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Master {
    String value() default "";
}

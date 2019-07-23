package com.example.springcloud.consumer.comsumer3.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author jbj
 * @create 2019-05-21 16:58
 */
@Slf4j
@WebFilter(urlPatterns = "/user/*")
public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("UserFilter -> init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("UserFilter -> doFilter -> before");
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("UserFilter -> doFilter - > after");
    }

    @Override
    public void destroy() {
        log.info("UserFilter -> destroy");
    }
}

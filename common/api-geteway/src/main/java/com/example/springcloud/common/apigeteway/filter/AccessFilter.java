package com.example.springcloud.common.apigeteway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jbj
 * @create 2019-05-06 11:40
 */
@Slf4j
@Configuration
public class AccessFilter extends ZuulFilter {


    /**
     * 过滤器类型， 决定过滤器在那个生命周期运行，
     * pre表示请求被路由之前执行
     * routing在路由请求时调用
     * post在routing和error过滤器之后被调用
     * error在请求发生错误是被调用
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器执行顺序，当有多个过滤器时，根据该值依次执行，越小优先级越高
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }


    /**
     * 判断该过滤器是否需要执行，直接返回true表示所有拦截都会执行
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 具体执行逻辑
     * @return
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info("send {} request to {} ", request.getMethod(), request.getRequestURL().toString());

        Object accessToken = request.getParameter("accessToken");
        if (accessToken == null) {
            log.warn("access is empty");
            //过滤该请求，不对其路由
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            return null;
        }
        log.info("accessToken ok");
        return null;
    }
}

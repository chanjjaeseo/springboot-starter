package com.springboot.demo.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class DefaultInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // 请求是否通过
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, java.lang.Object handler) throws Exception {
        return true;
    }

    // 后置处理
    public void postHandle(HttpServletRequest request, HttpServletResponse response, java.lang.Object handler,
                           ModelAndView modelAndView) throws Exception {
        logger.info("request finished processing ...");
    }
}

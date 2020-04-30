package com.coldcoder.iToken.common.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: DX
 * @Description: Spring Boot拦截器（初始化常量拦截器）
 * @Date: 2020/3/10 19:34
 * @Version: 1.0
 */
public class ConstantInterceptor implements HandlerInterceptor {
    private static final String HOST_CDN = "http://47.92.2.46:9000";
    private static final String TEMPLATE_ADMIN_LTE = "adminlte/v2.4.18/AdminLTE-2.4.18";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if(modelAndView != null){ //
            modelAndView.addObject("adminlte",HOST_CDN+"/"+TEMPLATE_ADMIN_LTE);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

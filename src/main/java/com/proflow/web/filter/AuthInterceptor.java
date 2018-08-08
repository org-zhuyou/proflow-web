package com.proflow.web.filter;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Leonid on 2018/8/6.
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    public AuthInterceptor() {

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(request.getRequestURL().toString());
        return super.preHandle(request, response, handler);
        //return false;
    }
}

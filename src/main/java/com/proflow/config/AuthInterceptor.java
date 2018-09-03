package com.proflow.config;

import com.alibaba.fastjson.JSON;
import com.proflow.annotation.NoAuth;
import com.proflow.entity.LocalSession;
import com.proflow.service.LocalSessionService;
import com.proflow.web.form.ResultForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Leonid on 2018/7/3.
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {


    public static final String _token = "_token";

    public static final String msg = "请登录后操作";

    public static final String session_key = "proflow_session";

    @Autowired
    protected LocalSessionService localSessionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof org.springframework.web.servlet.resource.ResourceHttpRequestHandler) {
            return super.preHandle(request, response, handler);
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        NoAuth auth = handlerMethod.getMethodAnnotation(NoAuth.class);
        if (null != auth) {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            //response.setHeader("Access-Control-Allow-Origin", "*");
            //response.setHeader("Access-Control-Allow-Headers", "x-auth-token,Content-Type");
            response.setCharacterEncoding("utf-8");
            String token = request.getHeader(_token);
            ResultForm resultForm = null;
            LocalSession localSession = localSessionService.getLocalSessionByToken(token);
            if (localSession != null) {
                request.getSession().setAttribute(session_key, localSession);
                return super.preHandle(request, response, handler);
            } else {
                resultForm = ResultForm.createError(msg);
                //System.out.println(JSON.toJSONString(resultForm));
                response.getWriter().print(JSON.toJSONString(resultForm));
                response.getWriter().flush();
                response.getWriter().close();
                return false;
            }
        }
        return super.preHandle(request, response, handler);
    }

}

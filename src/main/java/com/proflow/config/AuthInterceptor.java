package com.proflow.config;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import com.alibaba.fastjson.JSON;
import com.proflow.annotation.NoAuth;
import com.proflow.entity.LocalSession;
import com.proflow.entity.Menu;
import com.proflow.entity.Role;
import com.proflow.entity.vo.UserVO;
import com.proflow.service.LocalSessionService;
import com.proflow.service.UserService;
import com.proflow.web.constant.ResultCode;
import com.proflow.web.constant.SessionConstant;
import com.proflow.web.form.ResultForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Leonid on 2018/7/3.
 */
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {


    public static final String _token = "_token";

    public static final String msg = "请登录后操作";

    public static final String authMsg = "无操作权限";

    public static final String session_key = "proflow_session";

    @Autowired
    protected LocalSessionService localSessionService;
    @Autowired
    protected UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof org.springframework.web.servlet.resource.ResourceHttpRequestHandler) {
            return super.preHandle(request, response, handler);
        }

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            NoAuth auth = handlerMethod.getMethodAnnotation(NoAuth.class);
            if (null != auth) {
                response.setHeader("Content-type", "text/html;charset=UTF-8");
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setHeader("Access-Control-Allow-Headers", "x-auth-token,Content-Type");
                response.setHeader("Access-Control-Allow-Headers", "_token,Content-Type");
                response.setCharacterEncoding("utf-8");
                String token = request.getHeader(_token);
                ResultForm resultForm = null;
                LocalSession localSession = localSessionService.getLocalSessionByToken(token);
                if (localSession != null) {
                    UserVO userVO = userService.getUserVOById(localSession.getUserId());
                    request.getSession().setAttribute(session_key, localSession);
                    request.getSession().setAttribute(SessionConstant.SESSION_USER, userVO);
                    //
                    // TODO 判断是否有相关权限
                    String[] auths = auth.auth();
                    List<String> roles = userVO.getRoles().stream().map(e -> e.getCode()).collect(Collectors.toList());
                    List<String> aus = Arrays.asList(auths);

                    if (CollUtil.isNotEmpty(aus)) {
                        if (!roles.containsAll(aus)) {
                            resultForm = ResultForm.createError(ResultCode.NO_AUTH, authMsg);
                            response.getWriter().print(JSON.toJSONString(resultForm));
                            response.getWriter().flush();
                            response.getWriter().close();
                            return false;
                        }
                    }

                    return super.preHandle(request, response, handler);
                } else {
                    resultForm = ResultForm.createError(ResultCode.NO_LOGIN, msg);
                    response.getWriter().print(JSON.toJSONString(resultForm));
                    response.getWriter().flush();
                    response.getWriter().close();
                    return false;
                }
            }
        }
        return super.preHandle(request, response, handler);
    }

}

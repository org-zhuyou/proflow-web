package com.proflow.web.utils;


import com.proflow.config.AuthInterceptor;
import com.proflow.entity.LocalSession;
import com.proflow.entity.vo.UserVO;
import com.proflow.web.constant.SessionConstant;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Leonid on 2018/8/8.
 */
public class SessionUtil {

    public static UserVO getCurrentUser(HttpServletRequest request) {
        if (null == request) throw new IllegalArgumentException();
        return (UserVO) request.getSession().getAttribute(SessionConstant.SESSION_USER);
    }

    public static LocalSession getLocal(HttpServletRequest request) {
        Object obj = request.getSession().getAttribute(AuthInterceptor.session_key);
        return obj == null ? null : (LocalSession)obj;
    }

    public static Long getCurrentUserId(HttpServletRequest request) {
        LocalSession localSession = getLocal(request);
        return localSession == null ? 0 : localSession.getUserId();
    }

}

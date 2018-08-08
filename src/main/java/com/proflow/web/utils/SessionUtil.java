package com.proflow.web.utils;

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

}

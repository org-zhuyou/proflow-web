package com.proflow.web.controller;

import com.proflow.entity.vo.UserVO;
import com.proflow.service.UserService;
import com.proflow.web.constant.SessionConstant;
import com.proflow.web.form.ResultForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Leonid on 2018/8/6.
 */
@RestController
@RequestMapping("/")
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public Object login(String username, String password, HttpServletRequest request) {
        ResultForm<?> resultForm = null;
        try {
            UserVO userVO = userService.login(username, password);
            HttpSession session = request.getSession();
            session.setAttribute(SessionConstant.SESSION_USER, userVO);
            session.setAttribute(SessionConstant.SESSION_AUTH, true);
            session.setMaxInactiveInterval(60 * 60);
            resultForm = ResultForm.createSuccess("登录成功",userVO);
        } catch (Exception e) {
            resultForm = ResultForm.createError(e.getMessage());
        }
        return resultForm;
    }

}

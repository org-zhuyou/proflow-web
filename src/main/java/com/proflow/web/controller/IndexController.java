package com.proflow.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Leonid on 2018/8/6.
 */
@Controller
public class IndexController {


    @RequestMapping("/test1")
    @ResponseBody
    public String test1(HttpServletRequest request) {
        System.out.println(request.getSession().getId());
        return "ok";
    }

    @RequestMapping("/")
    public String index(HttpServletRequest request) {
        System.out.println(request.getSession().getId());
        System.out.println(request.getHeader("Cookie"));
        return "index";
    }

    @RequestMapping("/test")
    public String test() {
        return "test/index";
    }

}

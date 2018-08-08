package com.proflow.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Leonid on 2018/8/7.
 */
@RestController
@RequestMapping("/project")
public class ProjectController {

    private static Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @RequestMapping("/findById")
    public Object findById(Long id) {
        return null;
    }

    @RequestMapping("/find")
    public Object find() {
        return null;
    }
}

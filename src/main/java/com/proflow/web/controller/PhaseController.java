package com.proflow.web.controller;

import com.proflow.entity.ProjectPhase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Leonid on 2018/9/3.
 */
@RestController
@RequestMapping("/phase")
public class PhaseController {

    private static Logger logger = LoggerFactory.getLogger(PhaseController.class);

    @PostMapping("/createProjectPhase")
    public Object createProjectPhase(ProjectPhase projectPhase) {
        return null;
    }

}

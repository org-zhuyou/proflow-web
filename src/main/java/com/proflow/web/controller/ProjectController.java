package com.proflow.web.controller;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.proflow.entity.Project;
import com.proflow.service.ProjectContractService;
import com.proflow.service.ProjectService;
import com.proflow.web.form.PageForm;
import com.proflow.web.form.ResultForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Leonid on 2018/8/7.
 */
@RestController
@RequestMapping("/project")
public class ProjectController {

    private static Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectContractService projectContractService;

    @PostMapping("/saveProject")
    public Object saveProject(Project project) {
        ResultForm<?> resultForm = null;
        try {
            if (project.getId() == null) {
                throw new Exception("请通过创建合同新建项目信息");
            }
            if (project.getProjectContractId() == null) {
                throw new Exception("项目无合同信息");
            }
            projectService.insertOrUpdate(project);
            resultForm = ResultForm.createSuccess("保存成功", project);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }
        return resultForm;
    }

    @RequestMapping("/findById")
    public Object findById(Long id) {
        ResultForm<?> resultForm = null;
        try {
            Project project = projectService.selectById(id);
            resultForm = ResultForm.createSuccess("查询成功", project);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }
        return resultForm;
    }

    @RequestMapping("/page")
    public Object page(Project project, PageForm<Project> pageForm) {
        ResultForm<?> resultForm = null;
        try {
            EntityWrapper<Project> wrapper = new EntityWrapper<>();
            wrapper.setEntity(project);
            Page<Project> projectPage = projectService.selectPage(pageForm.createPage(), wrapper);
            resultForm = ResultForm.createSuccess("查询成功", projectPage);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }

        return resultForm;
    }

}

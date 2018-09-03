package com.proflow.web.controller;

import com.baomidou.mybatisplus.mapper.Condition;
import com.proflow.entity.Project;
import com.proflow.entity.ProjectContract;
import com.proflow.entity.ProjectSubpackage;
import com.proflow.service.ProjectService;
import com.proflow.service.ProjectSubpackageService;
import com.proflow.web.form.ResultForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Leonid on 2018/9/3.
 */
@RestController
@RequestMapping("/subpack")
public class SubpackageController {

    private static Logger logger = LoggerFactory.getLogger(SubpackageController.class);

    @Autowired
    private ProjectSubpackageService projectSubpackageService;
    @Autowired
    private ProjectService projectService;

    /**
     * 保存总包项目的分包信息
     * @param projectSubpackage
     * @return
     */
    @PostMapping("/saveSubPackage")
    public Object saveSubPackage(ProjectSubpackage projectSubpackage) {
        ResultForm<?> resultForm = null;
        try {
            if (projectSubpackage.getProjectId() == null) {
                throw new IllegalArgumentException();
            }

            Project project = projectService.selectById(projectSubpackage.getProjectId());
            if (project == null) {
                throw new Exception("项目信息不存在");
            }
            if (project.getType().equals(ProjectContract.SUB)) {
                throw new Exception("分包项目不能创建分包信息");
            }
            boolean result = projectSubpackageService.insertOrUpdate(projectSubpackage);
            resultForm = ResultForm.createSuccess("保存成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }
        return resultForm;
    }

    /**
     * 查询分包相关信息
     * @param projectId
     * @return
     */
    @PostMapping("/findProjectSubPackage")
    public Object findProjectSubPackage(Long projectId) {
        ResultForm<?> resultForm = null;
        try {
            List<ProjectSubpackage> subpackageList = projectSubpackageService.selectList(Condition.create().eq("project_id", projectId).orderBy("type", true));
            resultForm = ResultForm.createSuccess("查询成功", subpackageList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }
        return resultForm;
    }

}

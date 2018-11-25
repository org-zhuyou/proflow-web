package com.proflow.web.controller;

import com.baomidou.mybatisplus.mapper.Condition;
import com.proflow.annotation.NoAuth;
import com.proflow.entity.Project;
import com.proflow.entity.ProjectContract;
import com.proflow.entity.ProjectSubpackage;
import com.proflow.service.ProjectService;
import com.proflow.service.ProjectSubpackageService;
import com.proflow.web.form.ResultForm;
import com.proflow.web.utils.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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
    @NoAuth
    @PostMapping("/saveSubPackage")
    public Object saveSubPackage(ProjectSubpackage projectSubpackage, HttpServletRequest request) {
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
            if (projectSubpackage.getId() == null) {
                projectSubpackage.setCreateUser(SessionUtil.getCurrentUserId(request));
                projectSubpackage.setCreateTime(new Date());
            }
            boolean result = projectSubpackageService.insertOrUpdate(projectSubpackage);
            resultForm = ResultForm.createSuccess("保存成功", projectSubpackage);
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
    @NoAuth
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

    @NoAuth
    @PostMapping("/deleteProjectSubPackage")
    public Object deleteProjectSubPackage(Long subPackageId) {
        ResultForm<?> resultForm = null;
        try {
            if (projectSubpackageService.deleteById(subPackageId)) {
                resultForm = ResultForm.createSuccess("删除成功", null);
            } else {
                resultForm = ResultForm.createError("删除失败，请稍后再试");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }
        return resultForm;
    }

}

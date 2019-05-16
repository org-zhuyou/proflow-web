package com.proflow.web.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.proflow.annotation.NoAuth;
import com.proflow.em.RoleCodeEnum;
import com.proflow.entity.Project;
import com.proflow.entity.vo.ProjectVO;
import com.proflow.entity.vo.UserVO;
import com.proflow.service.ProjectContractService;
import com.proflow.service.ProjectPhaseAttachmentService;
import com.proflow.service.ProjectService;
import com.proflow.web.form.PageForm;
import com.proflow.web.form.ResultForm;
import com.proflow.web.utils.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Leonid on 2018/8/7.
 */
@RestController
@RequestMapping("/project")
public class ProjectController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectContractService projectContractService;
    @Autowired
    private ProjectPhaseAttachmentService projectPhaseAttachmentService;


    @NoAuth
    @PostMapping("/showProjectIds")
    public Object showProjectIds() {
        ResultForm<?> resultForm = null;
        try {
            Object obj = projectService.projectViewIds();
            resultForm = ResultForm.createSuccess("查询成功", obj);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError("系统繁忙，请稍后再试");
        }
        return resultForm;
    }

    @NoAuth
    @PostMapping("/projectView")
    public Object projectView(Long projectId) {
        ResultForm<?> resultForm = null;
        try {
            System.err.println(projectId);
            Object obj = projectService.projectView(projectId);
            System.err.println(JSON.toJSONString(obj));
            resultForm = ResultForm.createSuccess("查询成功", obj);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError("系统繁忙，请稍后再试");
        }
        return resultForm;
    }

    @NoAuth
    @PostMapping("/projectViewAll")
    public Object projectViewAll() {
        ResultForm<?> resultForm = null;

        try {
            List<Object> list = new ArrayList<>();

            List<Project> projects = this.projectService.selectList(Condition.create());

            for (Project project : projects) {
                Object obj = projectService.projectView(project.getId());
                list.add(obj);
            }

            resultForm = ResultForm.createSuccess("查询成功", list);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError("系统繁忙，请稍后再试");
        }


        return resultForm;
    }

    @NoAuth
    @PostMapping("/distributeOwner")
    public Object distributeOwner(Long projectId, Long ownerId) {
        ResultForm<?> resultForm = null;
        try {
            projectService.distributeOwner(ownerId, projectId);
            resultForm = ResultForm.createSuccess("分配成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError("系统繁忙，请稍后再试");
        }
        return resultForm;
    }

    @NoAuth
    @PostMapping("/deleteProject")
    public Object deleteProject(Long id) {
        ResultForm<?> resultForm = null;
        try {
            projectService.deleteProject(id);
            resultForm = ResultForm.createSuccess("删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }
        return resultForm;
    }
    @NoAuth
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


    @NoAuth()
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
    @NoAuth
    @RequestMapping("/page")
    public Object page(Project project, PageForm<ProjectVO> pageForm, HttpServletRequest request) {
        ResultForm<?> resultForm = null;
        try {

            //EntityWrapper<Project> wrapper = new EntityWrapper<>();


            UserVO userVO = SessionUtil.getCurrentUser(request);
            List<String> roleCodes = userVO.getRoles().stream().map(e -> e.getCode()).collect(Collectors.toList());
            if (roleCodes.contains(RoleCodeEnum.ADMIN.name())
                    || roleCodes.contains(RoleCodeEnum.BOSS.name()) || roleCodes.contains(RoleCodeEnum.CW.name())) {
                //logger.info("{pro}");
            } else {
                project.setOwnerId(userVO.getId());
            }
            //wrapper.setEntity(project);
            //Page<Project> projectPage = projectService.selectPage(pageForm.createPage(), wrapper);

            Page<ProjectVO> projectPage = projectService.pageProejct(pageForm.createPage(), project);
            resultForm = ResultForm.createSuccess("查询成功", projectPage);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }

        return resultForm;
    }

}

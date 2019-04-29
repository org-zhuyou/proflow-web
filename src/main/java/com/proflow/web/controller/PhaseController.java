package com.proflow.web.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.mapper.Condition;
import com.proflow.annotation.NoAuth;
import com.proflow.entity.*;
import com.proflow.entity.vo.ProjectPhaseAttrVO;
import com.proflow.service.*;
import com.proflow.web.form.ResultForm;
import com.proflow.web.utils.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Leonid on 2018/9/3.
 */
@RestController
@RequestMapping("/phase")
public class PhaseController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(PhaseController.class);

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectPhaseService projectPhaseService;

    @Autowired
    private ProjectPhaseFundService projectPhaseFundService;

    @Autowired
    private ProjectPhaseAttachmentService projectPhaseAttachmentService;

    @Autowired
    private ResourceAttachmentService resourceAttachmentService;

    /**
     * 项目节点款项列表
     * @param projectPhaseId
     * @return
     */
    @NoAuth
    @PostMapping("/listProjectPhaseFund")
    public Object listProjectPhaseFund(Long projectPhaseId) {
        ResultForm<?> resultForm = null;
        try {
            List<ProjectPhaseFund> list = projectPhaseFundService.selectList(Condition.create().eq("project_phase_id", projectPhaseId));
            resultForm = ResultForm.createSuccess("查询成功", list);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }
        return resultForm;
    }

    /**
     * 保存节点款项信息
     * @param projectPhaseFund
     * @param request
     * @return
     */
    @NoAuth
    @PostMapping("/saveProjectPhaseFund")
    public Object saveProjectPhaseFund(ProjectPhaseFund projectPhaseFund, HttpServletRequest request) {
        ResultForm<?> resultForm = null;

        Project project = null;
        ProjectPhase projectPhase = null;
        try {
            if (projectPhaseFund.getId() == null) {
                if (projectPhaseFund.getProjectId() == null) {
                    throw new IllegalArgumentException();
                }
                if (projectPhaseFund.getProjectPhaseId() == null) {
                    throw new IllegalArgumentException();
                }
                project = projectService.selectById(projectPhaseFund.getProjectId());
                if (project == null) {
                    throw new Exception("项目不存在");
                }
                projectPhase = projectPhaseService.selectById(projectPhaseFund.getProjectPhaseId());
                if (projectPhase == null) {
                    throw new Exception("项目节点不存在");
                }
                projectPhaseFund.setStatus(0);
                projectPhaseFund.setCreateTime(new Date());
                projectPhaseFund.setCreateUser(SessionUtil.getCurrentUserId(request));
            } else {
                ProjectPhaseFund projectPhaseFund2 = projectPhaseFundService.selectById(projectPhaseFund.getId());
                CopyOptions copyOptions = CopyOptions.create();
                copyOptions.setIgnoreNullValue(true);
                BeanUtil.copyProperties(projectPhaseFund2, projectPhaseFund, copyOptions);
                if (projectPhaseFund.getComplete()!= null) {
                    projectPhaseFund.setValidator(SessionUtil.getCurrentUser(request).getName());
                }
            }

            if (projectPhaseFundService.insertOrUpdate(projectPhaseFund)) {
                resultForm = ResultForm.createSuccess("保存成功", projectPhaseFund);
            } else {
                resultForm = ResultForm.createError("保存失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }
        return resultForm;
    }

    /**
     * 项目节点列表
     * @param projectId
     * @return
     */
    @NoAuth
    @PostMapping("/listProjectPhase")
    public Object listProjectPhase(Long projectId) {
        ResultForm<?> resultForm = null;
        try {
            List<ProjectPhase> list = projectPhaseService.selectList(Condition.create().eq("project_id", projectId));
            resultForm = ResultForm.createSuccess("查询成功", list);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }
        return resultForm;
    }

    /**
     * 保存项目节点信息
     * @param projectPhase
     * @param request
     * @return
     */
    @NoAuth
    @PostMapping("/saveProjectPhase")
    public Object saveProjectPhase(ProjectPhase projectPhase, HttpServletRequest request) {
        ResultForm<?> resultForm = null;
        if (projectPhase.getProjectId() == null) {
            throw new IllegalArgumentException();
        }
        Project project = null;
        try {
            project = projectService.selectById(projectPhase.getProjectId());
            if (project == null) {
                throw new Exception("项目不存在");
            }
            if (projectPhase.getId() == null) {
                projectPhase.setComplete(0);
                projectPhase.setCreateTime(new Date());
                projectPhase.setCreateUser(SessionUtil.getCurrentUserId(request));
            }
            if (projectPhaseService.insertOrUpdate(projectPhase)) {
                resultForm = ResultForm.createSuccess("保存成功", projectPhase);
            } else {
                resultForm = ResultForm.createError("保存失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }
        return resultForm;
    }

    @NoAuth
    @PostMapping("/deleteProjectPhaseFund")
    public Object deleteProjectPhaseFund(Long phaseFundId) {
        ResultForm<?> resultForm = null;
        try {
            ProjectPhaseFund projectPhaseFund = this.projectPhaseFundService.selectById(phaseFundId);
            if (projectPhaseFund == null) {
                throw new IllegalArgumentException("节点款项信息不存在");
            }
            if (projectPhaseFund.getStatus().equals(1)) {
                throw new Exception("当前款项信息已确认，无法删除");
            }
            if (this.projectPhaseFundService.deleteById(phaseFundId)) {
                resultForm = ResultForm.createSuccess("删除成功", null);
            } else {
                resultForm = ResultForm.createError("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }
        return resultForm;
    }

    /**
     * 删除项目节点
     * @param phaseId
     * @return
     */
    @NoAuth
    @PostMapping("/deleteProjectPhase")
    public Object deleteProjectPhase(Long phaseId) {
        ResultForm<?> resultForm = null;
        // 逻辑删除
        try {
            ProjectPhase projectPhase = this.projectPhaseService.selectById(phaseId);
            if (projectPhase == null) {
                throw new Exception("节点信息不存在");
            }
            // 如果项目有进度  不可以删除
            if (projectPhase.getComplete() > 0) {
                throw new Exception("项目有进度，无法删除");
            }
            List<ProjectPhaseFund> list = projectPhaseFundService.selectList(Condition.create().eq("project_phase_id", projectPhase.getId()));
            List<Long> deleteIds = new ArrayList<>();
            if (CollUtil.isNotEmpty(list)) {
                for (ProjectPhaseFund fund : list) {
                    // 如果财务已经确认
                    if (fund.getStatus().equals(1)) {
                        throw new Exception("当前节点存在已确认款项信息，不可以删除");
                    }
                    deleteIds.add(fund.getId());
                }
                this.projectPhaseFundService.deleteBatchIds(deleteIds);
            }

            this.projectPhaseService.deleteById(phaseId);
            resultForm = ResultForm.createSuccess("删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }
        return resultForm;
    }

    /**
     * 上传节点附件
     * @param phaseId
     * @return
     */
    @NoAuth
    @PostMapping("/uploadPhaseResource")
    public Object uploadPhaseResource(Long phaseId, @RequestParam("file") MultipartFile file) {
        ResultForm<?> resultForm = null;
        try {
            ResourceAttachment resourceAttachment = projectPhaseService.uploadPhaseAttachment(phaseId, file);
            resultForm = ResultForm.createSuccess("上传成功", resourceAttachment);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }
        return resultForm;
    }


    /**
     * 查询节点附件列表
     * @param phaseId
     * @return
     */
    @NoAuth
    @PostMapping("/listProjectPhaseAttrsVO")
    public Object listProjectPhaseAttrsVO(Long phaseId) {
        ResultForm<?> resultForm = null;
        try {
            List<ProjectPhaseAttrVO> projectPhaseAttrVOList = projectPhaseService.getProjectPhaseAttrsVO(phaseId);
            resultForm = ResultForm.createSuccess("查询成功", projectPhaseAttrVOList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }
        return resultForm;
    }

    /**
     * 删除资源信息
     * @param resourceId
     * @return
     */
    @NoAuth
    @PostMapping("/deletePhaseResource")
    public Object deletePhaseResource(Long resourceId) {
        ResultForm<?> resultForm = null;
        try {
            ProjectPhaseAttachment projectPhaseAttachment = projectPhaseAttachmentService.selectById(resourceId);
            if (projectPhaseAttachment == null) {
                throw new Exception("附件信息不存在");
            }
            Long resourceAttrId = projectPhaseAttachment.getResourceAttachmentId();
            projectPhaseAttachmentService.deleteById(resourceId);

            ResourceAttachment resourceAttachment = this.resourceAttachmentService.selectById(resourceAttrId);
            if (resourceAttachment != null) {
                //String filepath = resourceAttachment.getFilePath();
                this.resourceAttachmentService.deleteById(resourceAttachment.getId());
                //new File(filepath).delete();
            }

            resultForm = ResultForm.createSuccess("删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }
        return resultForm;
    }



}

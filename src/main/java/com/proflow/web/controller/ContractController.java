package com.proflow.web.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.proflow.entity.ProjectContract;
import com.proflow.entity.ProjectContractResource;
import com.proflow.entity.ResourceAttachment;
import com.proflow.entity.vo.ProjectContractResourceVO;
import com.proflow.service.ProjectContractResourceService;
import com.proflow.service.ProjectContractService;
import com.proflow.service.ResourceAttachmentService;
import com.proflow.web.form.PageForm;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Leonid on 2018/8/7.
 */
@RestController
@RequestMapping("/contract")
public class ContractController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(ContractController.class);

    @Autowired
    private ProjectContractService projectContractService;
    @Autowired
    private ProjectContractResourceService projectContractResourceService;
    @Autowired
    private ResourceAttachmentService resourceAttachmentService;

    /**
     * 保存合同
     * @param contract
     * @param createProject
     * @return
     */
    @PostMapping("/save")
    public Object save(ProjectContract contract, Boolean createProject, HttpServletRequest request) {
        ResultForm<?> resultForm = null;
        try {
            Long userId = SessionUtil.getCurrentUserId(request);
            contract.setCreateUser(userId);
            ProjectContract projectContract = projectContractService.save(contract, createProject);
            resultForm = ResultForm.createSuccess("保存成功", projectContract);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }
        return resultForm;
    }


    /**
     * 删除合同
     * @param contractId
     * @return
     */
    @PostMapping("/delete")
    public Object delete(Long contractId) {
        ResultForm<?> resultForm = null;
        try {
            projectContractService.deleteById(contractId);
            resultForm = ResultForm.createSuccess("删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }
        return resultForm;
    }

    /**
     * 通过id查询合同信息
     * @param id
     * @return
     */
    @PostMapping("/findById")
    public Object findById(Long id) {
        ResultForm<?> resultForm = null;
        try {
            ProjectContract projectContract = projectContractService.selectById(id);
            List<ProjectContractResourceVO> list = projectContractService.getProjectContractVO(id);
            Map<String, Object> result = new HashMap<>();
            result.put("contract", projectContract);
            result.put("resource", list);
            // 获取合同附件
            resultForm = ResultForm.createSuccess("保存成功", result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }

        return resultForm;
    }

    /**
     * 查询合同相关资源
     * @param contractId
     * @return
     */
    @PostMapping("/findContractResourceVO")
    public Object findContractResourceVO(Long contractId) {
        ResultForm<?> resultForm = null;
        try {
            List<ProjectContractResourceVO> list = projectContractService.getProjectContractVO(contractId);
            resultForm = ResultForm.createSuccess("查询成功", list);
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
    @PostMapping("/deleteContractResource")
    public Object deleteContractResource(Long resourceId) {
        ResultForm<?> resultForm = null;
        try {
            ProjectContractResource projectContractResource = projectContractResourceService.selectById(resourceId);
            if (projectContractResource == null) {
                throw new Exception("附件信息不存在");
            }
            Long resourceAttrId = projectContractResource.getResourceAttachmentId();
            projectContractResourceService.deleteById(resourceId);

            ResourceAttachment resourceAttachment = this.resourceAttachmentService.selectById(resourceAttrId);
            if (resourceAttachment != null) {
                String filepath = resourceAttachment.getFilePath();
                this.resourceAttachmentService.deleteById(resourceAttachment.getId());
                new File(filepath).delete();
            }

            resultForm = ResultForm.createSuccess("删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }
        return resultForm;
    }

    /**
     * 分页查看合同信息
     * @param pageForm
     * @param projectContract
     * @return
     */
    @PostMapping("/page")
    public Object page(PageForm<ProjectContract> pageForm, ProjectContract projectContract) {
        ResultForm<?> resultForm = null;
        Page<ProjectContract> page = pageForm.createPage();
        try {
            page = projectContractService.page(page, projectContract);
            resultForm = ResultForm.createSuccess("查询成功", page);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }
        return resultForm;
    }

    /**
     * 上传合同附件
     * @param contractId
     * @return
     */
    @PostMapping("/uploadContractResource")
    public Object uploadContractResource(Long contractId, @RequestParam("file") MultipartFile file) {
        ResultForm<?> resultForm = null;
        try {
            ResourceAttachment resourceAttachment = projectContractService.uploadContractAttachment(contractId, file);
            resultForm = ResultForm.createSuccess("上传成功", resourceAttachment);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }
        return resultForm;
    }



}

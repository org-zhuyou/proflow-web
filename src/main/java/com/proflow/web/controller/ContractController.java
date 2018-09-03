package com.proflow.web.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.proflow.entity.ProjectContract;
import com.proflow.entity.vo.ProjectContractResourceVO;
import com.proflow.service.ProjectContractService;
import com.proflow.web.form.PageForm;
import com.proflow.web.form.ResultForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Leonid on 2018/8/7.
 */
@RestController
@RequestMapping("/contract")
public class ContractController {

    private static Logger logger = LoggerFactory.getLogger(ContractController.class);

    @Autowired
    private ProjectContractService projectContractService;

    // 保存合同
    @PostMapping("/save")
    public Object save(ProjectContract contract, Boolean createProject) {
        ResultForm<?> resultForm = null;
        try {
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
            resultForm = ResultForm.createSuccess("保存成功", projectContract);
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
            resultForm = ResultForm.createSuccess("保存成功", page);
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
            projectContractService.uploadContractAttachment(contractId, file);
            resultForm = ResultForm.createSuccess("上传成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }
        return resultForm;
    }



}

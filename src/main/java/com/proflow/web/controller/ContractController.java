package com.proflow.web.controller;

import com.proflow.entity.ProjectContract;
import com.proflow.service.ProjectContractService;
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
        return null;
    }

    /**
     * 通过id查询合同信息
     * @param id
     * @return
     */
    @PostMapping("/findById")
    public Object findById(Long id) {
        ResultForm<?> resultForm = null;
        //projectContractService.selectById(id);
        //获取所有合同附件
        return null;
    }

    @PostMapping("/find")
    public Object find() {
        return null;
    }

    /**
     * 上传合同附件
     * @param contractId
     * @return
     */
    @PostMapping("/uploadContractResource")
    public Object uploadContractResource(Long contractId) {
        return null;
    }



}

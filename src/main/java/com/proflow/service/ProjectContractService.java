package com.proflow.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.proflow.entity.ProjectContract;
import com.baomidou.mybatisplus.service.IService;
import com.proflow.entity.ResourceAttachment;
import com.proflow.entity.vo.ProjectContractResourceVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 项目合同 服务类
 * </p>
 *
 * @author Leonid
 * @since 2018-08-06
 */
public interface ProjectContractService extends IService<ProjectContract> {

    ProjectContract save(ProjectContract projectContract, Boolean createProject) throws Exception;

    Page<ProjectContract> page(Page<ProjectContract> page, ProjectContract condition) throws Exception;

    ResourceAttachment uploadContractAttachment(Long contractId, MultipartFile file) throws Exception;

    List<ProjectContractResourceVO> getProjectContractVO(Long contractId) throws Exception;

}

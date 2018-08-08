package com.proflow.service;

import com.proflow.entity.ProjectContract;
import com.baomidou.mybatisplus.service.IService;

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

}

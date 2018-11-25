package com.proflow.service;

import com.proflow.entity.Project;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 项目表 服务类
 * </p>
 *
 * @author Leonid
 * @since 2018-08-06
 */
public interface ProjectService extends IService<Project> {

    Project findProjectByContractId(Long contractId) throws Exception;

    void deleteProject(Long id) throws Exception;

}

package com.proflow.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.proflow.entity.Project;
import com.baomidou.mybatisplus.service.IService;
import com.proflow.entity.vo.ProjectVO;

/**
 * <p>
 * 项目表 服务类
 * </p>
 *
 * @author Leonid
 * @since 2018-08-06
 */
public interface ProjectService extends IService<Project> {


    Object projectView(Long projectId) throws Exception;

    Page<ProjectVO> pageProejct(Page<ProjectVO> page, Project project) throws Exception;

    Project findProjectByContractId(Long contractId) throws Exception;

    void deleteProject(Long id) throws Exception;

    /**
     * 分配所属人
     * @param ownerId
     * @param projectId
     * @throws Exception
     */
    boolean distributeOwner(Long ownerId, Long projectId) throws Exception;

}

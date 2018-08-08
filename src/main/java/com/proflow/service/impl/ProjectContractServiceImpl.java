package com.proflow.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.proflow.entity.Project;
import com.proflow.entity.ProjectContract;
import com.proflow.mapper.ProjectContractMapper;
import com.proflow.service.ProjectContractService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.proflow.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 项目合同 服务实现类
 * </p>
 *
 * @author Leonid
 * @since 2018-08-06
 */
@Service
public class ProjectContractServiceImpl extends ServiceImpl<ProjectContractMapper, ProjectContract> implements ProjectContractService {

    @Autowired
    private ProjectService projectService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProjectContract save(ProjectContract projectContract, Boolean createProject) throws Exception {
        if (ObjectUtil.isNull(projectContract)) {
            throw new IllegalArgumentException();
        }
        // 如果是修改的话  不修改项目信息
        //createProject = projectContract.getId() != null ? false : createProject;
        Long contractId = projectContract.getId();
        if (!this.insertOrUpdate(projectContract)) {
            throw new Exception("合同信息保存失败");
        }
        Project project = null;
        if (createProject) {
            if (ObjectUtil.isNotNull(contractId)) {
                project = projectService.findProjectByContractId(contractId);
                project = createProjectByContract(projectContract, project);
            } else {
                project = createProjectByContract(projectContract, new Project());
            }
        }
        if (!projectService.insertOrUpdate(project)) {
            throw new Exception("项目信息保存失败");
        }
        return projectContract;
    }


    private Project createProjectByContract(ProjectContract projectContract, Project project) {
        project.setProjectContractId(projectContract.getId());
        project.setAddress(projectContract.getAddress());
        project.setEndDate(projectContract.getEndDate());
        project.setStartDate(projectContract.getStartDate());
        project.setPartyA(projectContract.getPartyA());
        project.setPartyB(projectContract.getPartyB());
        project.setTimeLimit(projectContract.getTimeLimit());
        project.setTotalAmount(projectContract.getTotalAmount());
        project.setType(projectContract.getType());
        return project;
    }
}

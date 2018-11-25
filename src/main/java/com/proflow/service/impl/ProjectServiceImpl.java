package com.proflow.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.proflow.entity.*;
import com.proflow.mapper.ProjectMapper;
import com.proflow.service.*;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 项目表 服务实现类
 * </p>
 *
 * @author Leonid
 * @since 2018-08-06
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

    @Autowired
    private ProjectPhaseService projectPhaseService;

    @Autowired
    private ProjectPhaseFundService projectPhaseFundService;

    @Autowired
    private ProjectSubpackageService projectSubpackageService;

    @Autowired
    private ProjectPhaseAttachmentService projectPhaseAttachmentService;

    @Override
    public Project findProjectByContractId(Long contractId) throws Exception {
        if (ObjectUtil.isNull(contractId)) {
            throw new IllegalArgumentException();
        }
        Project project = new Project();
        project.setProjectContractId(contractId);
        EntityWrapper<Project> wrapper = new EntityWrapper<>();
        wrapper.setEntity(project);
        return this.selectOne(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteProject(Long id) throws Exception {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        Project project = this.selectById(id);
        if (project == null) {
            throw new Exception("项目不存在");
        }

//        List<ProjectPhase> projectPhaseList = projectPhaseService.selectList(Condition.create().eq("project_id", id));
//        List<ProjectPhaseFund> projectPhaseFundList = projectPhaseFundService.selectList(Condition.create().eq("project_id", id));
//        List<ProjectSubpackage> projectSubpackageList = projectSubpackageService.selectList(Condition.create().eq("project_id", id));
//        List<ProjectPhaseAttachment> projectPhaseAttachmentList = projectPhaseAttachmentService.selectList(Condition.create().eq("project_id", id));

        this.deleteById(id);
        this.projectPhaseService.delete(Condition.create().eq("project_id", id));
        this.projectPhaseFundService.delete(Condition.create().eq("project_id", id));
        this.projectSubpackageService.delete(Condition.create().eq("project_id", id));
        this.projectPhaseAttachmentService.delete(Condition.create().eq("project_id", id));

    }
}

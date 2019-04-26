package com.proflow.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.proflow.entity.*;
import com.proflow.entity.vo.ProjectPhaseAttrVO;
import com.proflow.entity.vo.ProjectVO;
import com.proflow.mapper.ProjectMapper;
import com.proflow.service.*;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private UserService userService;

    @Override
    public Object projectView(Long projectId) throws Exception {
        if (projectId == null) {
            throw new IllegalArgumentException();
        }
        // 项目信息
        Project project = this.selectById(projectId);
        List<ProjectPhase> projectPhases = this.projectPhaseService.selectList(Condition.create().eq("project_Id", projectId));
        List<ProjectPhaseAttrVO> vos = new ArrayList<>();

        for (ProjectPhase projectPhase : projectPhases) {
            List<ProjectPhaseAttrVO> volist = projectPhaseService.getProjectPhaseAttrsVO(projectPhase.getId());
            if (CollUtil.isNotEmpty(volist)) {

                vos.addAll(volist.stream().map(e -> {
                    e.setProjectPhase(projectPhase);
                    return e;
                }).collect(Collectors.toList()));
            }

        }

        Map<String, Object> result = new HashMap<>();
        result.put("project", project);
        result.put("phaseAttr", vos);
        return result;
    }

    @Override
    public Page<ProjectVO> pageProejct(Page<ProjectVO> page, Project project) throws Exception {
        if (page == null) {
            throw new IllegalArgumentException();
        }
        List<ProjectVO> projectVOS = this.baseMapper.listProject(page, project);
        page.setRecords(projectVOS);
        return page;
    }

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

    @Override
    public boolean distributeOwner(Long ownerId, Long projectId) throws Exception {
        if (ownerId == null || projectId == null) {
            throw new Exception("参数错误");
        }
        User user = userService.selectById(ownerId);
        if (user == null) {
            throw new Exception("未知的项目所属人");
        }

        Project project = this.selectById(projectId);
        if (project == null) {
            throw new Exception("项目信息有误");
        }

        project.setOwnerId(ownerId);
        return project.updateById();
    }
}

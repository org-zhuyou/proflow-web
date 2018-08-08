package com.proflow.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.proflow.entity.Project;
import com.proflow.mapper.ProjectMapper;
import com.proflow.service.ProjectService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
}

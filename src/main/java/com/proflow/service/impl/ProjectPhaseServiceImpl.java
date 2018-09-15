package com.proflow.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.mapper.Condition;
import com.proflow.entity.*;
import com.proflow.entity.vo.ProjectContractResourceVO;
import com.proflow.entity.vo.ProjectPhaseAttrVO;
import com.proflow.mapper.ProjectPhaseMapper;
import com.proflow.service.ProjectPhaseAttachmentService;
import com.proflow.service.ProjectPhaseService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.proflow.service.ResourceAttachmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 项目节点表 服务实现类
 * </p>
 *
 * @author Leonid
 * @since 2018-08-06
 */
@Service
public class ProjectPhaseServiceImpl extends ServiceImpl<ProjectPhaseMapper, ProjectPhase> implements ProjectPhaseService {

    private static Logger logger = LoggerFactory.getLogger(ProjectPhaseServiceImpl.class);

    @Value("${resource.local.path}")
    private String RESOURCE_LOCAL_PATH;

    @Autowired
    private ProjectPhaseAttachmentService projectPhaseAttachmentService;

    @Autowired
    private ResourceAttachmentService resourceAttachmentService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResourceAttachment uploadPhaseAttachment(Long phaseId, MultipartFile file) throws Exception {
        if (phaseId == null) {
            throw new IllegalArgumentException();
        }
        ProjectPhase projectPhase = this.selectById(phaseId);
        if(null == projectPhase) {
            throw new Exception("节点信息不存在");
        }
        Long time = System.currentTimeMillis();
        // TODO 保存在本地
        File file1 = new File(RESOURCE_LOCAL_PATH + "/" + time + file.getOriginalFilename());
        file.transferTo(file1);

        // 创建个资源 ResourceAttachment
        ResourceAttachment resourceAttachment = new ResourceAttachment();
        resourceAttachment.setFilePath(file1.getPath());
        resourceAttachment.setName(file1.getName());
        resourceAttachment.setSize(file1.length());
        resourceAttachment.setSuffix(FileUtil.extName(file1.getName()));
        resourceAttachment.setType(ResourceAttachment.LOCAL);
        resourceAttachment.setCreateTime(new Date());
        resourceAttachmentService.insert(resourceAttachment);
        // 创建节点资源信息

        ProjectPhaseAttachment projectPhaseAttachment = new ProjectPhaseAttachment();
        projectPhaseAttachment.setProjectId(projectPhase.getProjectId());
        projectPhaseAttachment.setProjectPhaseId(projectPhase.getId());
        projectPhaseAttachment.setResourceAttachmentId(resourceAttachment.getId());
        projectPhaseAttachmentService.insert(projectPhaseAttachment);
        return resourceAttachment;
    }

    @Override
    public List<ProjectPhaseAttrVO> getProjectPhaseAttrsVO(Long phaseId) throws Exception {
        if (phaseId == null) {
            throw new IllegalArgumentException();
        }
        ProjectPhase projectPhase = this.selectById(phaseId);
        if(null == projectPhase) {
            throw new Exception("节点信息不存在");
        }

        List<ProjectPhaseAttachment> projectPhaseAttachments = projectPhaseAttachmentService.selectList
                (Condition.create().eq("project_phase_id", phaseId));
        List<ProjectPhaseAttrVO> voList = new ArrayList<>();
        for (ProjectPhaseAttachment attachment : projectPhaseAttachments) {
            ProjectPhaseAttrVO vo = new ProjectPhaseAttrVO();
            BeanUtil.copyProperties(attachment, vo);


            ResourceAttachment resourceAttachment = resourceAttachmentService.selectById(vo.getResourceAttachmentId());

            vo.setResourceAttachment(resourceAttachment);
            voList.add(vo);
        }
        return voList;
    }
}

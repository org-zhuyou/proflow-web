package com.proflow.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.mapper.Condition;
import com.proflow.em.RrcFileType;
import com.proflow.entity.*;
import com.proflow.entity.vo.ProjectContractResourceVO;
import com.proflow.entity.vo.ProjectPhaseAttrVO;
import com.proflow.mapper.ProjectPhaseMapper;
import com.proflow.service.OSSObjectService;
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

import static java.util.Arrays.asList;

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

    @Autowired
    private OSSObjectService ossObjectService;

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
        /*
        File file1 = new File(RESOURCE_LOCAL_PATH + "/" + time + file.getOriginalFilename());
        file.transferTo(file1);
        */

        // TODO 保存在OSS上
        String ossPath = ossObjectService.uploadInputStream2OSS(file.getInputStream(), file.getOriginalFilename());

        // 创建个资源 ResourceAttachment
        ResourceAttachment resourceAttachment = new ResourceAttachment();
        //resourceAttachment.setFilePath(file1.getPath());
        resourceAttachment.setUrl(ossPath);
        resourceAttachment.setName(file.getOriginalFilename());
        resourceAttachment.setSize(file.getSize());
        resourceAttachment.setSuffix(FileUtil.extName(file.getOriginalFilename()));
        resourceAttachment.setType(ResourceAttachment.REMOTE);
        resourceAttachment.setFileType(getRrcFileTypeBySuffix(resourceAttachment.getSuffix()));

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

    private Integer getRrcFileTypeBySuffix(String suffix) {
        List<String> videoArr = asList("AVI","MOV","RMVB","RM","FLV","MP4","3GP");

        List<String> imageArr = asList("JPG","JPEG","GIF","PNG");
        if (videoArr.contains(suffix.toUpperCase())) {
            return RrcFileType.VIDEO.getId();
        } else if (imageArr.contains(suffix.toUpperCase())){
            return RrcFileType.IMAGE.getId();
        } else {
            return RrcFileType.OTHER.getId();
        }

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
                (Condition.create().eq("project_phase_id", phaseId).orderBy("id",false));
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

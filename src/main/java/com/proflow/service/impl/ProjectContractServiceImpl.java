package com.proflow.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.proflow.em.RrcFileType;
import com.proflow.entity.Project;
import com.proflow.entity.ProjectContract;
import com.proflow.entity.ProjectContractResource;
import com.proflow.entity.ResourceAttachment;
import com.proflow.entity.vo.ProjectContractResourceVO;
import com.proflow.mapper.ProjectContractMapper;
import com.proflow.service.*;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
    @Autowired
    private ResourceAttachmentService resourceAttachmentService;
    @Autowired
    private ProjectContractResourceService projectContractResourceService;
    @Autowired
    private ProjectContractService projectContractService;
    @Autowired
    private OSSObjectService ossObjectService;

    @Value("${resource.local.path}")
    private String RESOURCE_LOCAL_PATH;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProjectContract save(ProjectContract projectContract, Boolean createProject) throws Exception {
        if (ObjectUtil.isNull(projectContract)) {
            throw new IllegalArgumentException();
        }
        // 如果是修改的话  不修改项目信息
        //createProject = projectContract.getId() != null ? false : createProject;
        Long contractId = projectContract.getId();
        projectContract.setCreateTime(new Date());
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
            if (!projectService.insertOrUpdate(project)) {
                throw new Exception("项目信息保存失败");
            }
        }

        return projectContract;
    }

    @Override
    public Page<ProjectContract> page(Page<ProjectContract> page, ProjectContract condition) throws Exception {
        if (ObjectUtil.isNull(page)) {
            throw new IllegalArgumentException();
        }
        EntityWrapper<ProjectContract> wrapper = new EntityWrapper<>();
        wrapper.setEntity(condition);
        return this.selectPage(page, wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResourceAttachment uploadContractAttachment(Long contractId, MultipartFile file) throws Exception {
        if (contractId == null) {
            throw new IllegalArgumentException();
        }
        ProjectContract projectContract = this.selectById(contractId);
        if(null == projectContract) {
            throw new Exception("合同信息不存在");
        }
        Long time = System.currentTimeMillis();
        // TODO 保存在本地
        //File file1 = new File(RESOURCE_LOCAL_PATH + "/" + time + file.getOriginalFilename());
        //file.transferTo(file1);

        // TODO 保存在OSS上
        String ossPath = ossObjectService.uploadInputStream2OSS(file.getInputStream(), file.getOriginalFilename());


        // 创建个资源 ResourceAttachment
        ResourceAttachment resourceAttachment = new ResourceAttachment();
        resourceAttachment.setUrl(ossPath);
        resourceAttachment.setName(file.getOriginalFilename());
        resourceAttachment.setSize(file.getSize());
        resourceAttachment.setSuffix(FileUtil.extName(file.getOriginalFilename()));
        resourceAttachment.setType(ResourceAttachment.REMOTE);

        resourceAttachment.setFileType(getRrcFileTypeBySuffix(resourceAttachment.getSuffix()));

        resourceAttachment.setCreateTime(new Date());
        resourceAttachmentService.insert(resourceAttachment);
        // 创建个合同资源表 ProjectContractResource
        ProjectContractResource projectContractResource = new ProjectContractResource();
        projectContractResource.setName(projectContract.getName() + "-" + resourceAttachment.getName());
        projectContractResource.setResourceAttachmentId(resourceAttachment.getId());
        projectContractResource.setProjectContractId(projectContract.getId());
        projectContractResource.setCreateTime(new Date());
        projectContractResource.setSort(0);
        projectContractResourceService.insert(projectContractResource);

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
    public List<ProjectContractResourceVO> getProjectContractVO(Long contractId) throws Exception {
        if (contractId == null) {
            throw new IllegalArgumentException();
        }
        ProjectContract projectContract = this.selectById(contractId);
        if(null == projectContract) {
            throw new Exception("合同信息不存在");
        }

        List<ProjectContractResource> projectContractList = projectContractResourceService.selectList
                (Condition.create().eq("project_contract_id", contractId).orderBy("create_time", true));
        List<ProjectContractResourceVO> voList = new ArrayList<>();
        for (ProjectContractResource contract : projectContractList) {
            ProjectContractResourceVO vo = new ProjectContractResourceVO();
            BeanUtil.copyProperties(contract, vo);
            ResourceAttachment resourceAttachment = resourceAttachmentService.selectById(vo.getResourceAttachmentId());
            vo.setResourceAttachment(resourceAttachment);
            voList.add(vo);
        }
        return voList;
    }


    private Project createProjectByContract(ProjectContract projectContract, Project project) {
        project.setName(projectContract.getName());
        project.setProjectContractId(projectContract.getId());
        project.setAddress(projectContract.getAddress());
        project.setEndDate(projectContract.getEndDate());
        project.setStartDate(projectContract.getStartDate());
        project.setPartyA(projectContract.getPartyA());
        project.setPartyB(projectContract.getPartyB());
        project.setTimeLimit(projectContract.getTimeLimit());
        project.setTotalAmount(projectContract.getTotalAmount());
        project.setType(projectContract.getType());
        project.setCreateTime(new Date());
        project.setCreateUser(projectContract.getCreateUser());
        return project;
    }
}

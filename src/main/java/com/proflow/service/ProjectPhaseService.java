package com.proflow.service;

import com.proflow.entity.ProjectPhase;
import com.baomidou.mybatisplus.service.IService;
import com.proflow.entity.ResourceAttachment;
import com.proflow.entity.vo.ProjectPhaseAttrVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 项目节点表 服务类
 * </p>
 *
 * @author Leonid
 * @since 2018-08-06
 */
public interface ProjectPhaseService extends IService<ProjectPhase> {

    ResourceAttachment uploadPhaseAttachment(Long phaseId, MultipartFile file) throws Exception;

    List<ProjectPhaseAttrVO> getProjectPhaseAttrsVO(Long phaseId) throws Exception;

}

package com.proflow.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.proflow.entity.Project;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.proflow.entity.vo.ProjectVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 项目表 Mapper 接口
 * </p>
 *
 * @author Leonid
 * @since 2018-08-06
 */
public interface ProjectMapper extends BaseMapper<Project> {

    List<ProjectVO> listProject(Page<ProjectVO> page, @Param("project") Project project);

    List<String> projectViewIds();

}

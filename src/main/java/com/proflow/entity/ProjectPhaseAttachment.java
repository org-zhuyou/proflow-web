package com.proflow.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 项目节点附件表
 * </p>
 *
 * @author Leonid
 * @since 2018-08-06
 */
public class ProjectPhaseAttachment extends Model<ProjectPhaseAttachment> {

    private static final long serialVersionUID = 1L;

	private Long id;
	private Long resourceAttachmentId;
    /**
     * 项目节点id
     */
	private Long projectPhaseId;
    /**
     * 项目id
     */
	private Long projectId;
    /**
     * 描述
     */
	private String desc;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getResourceAttachmentId() {
		return resourceAttachmentId;
	}

	public void setResourceAttachmentId(Long resourceAttachmentId) {
		this.resourceAttachmentId = resourceAttachmentId;
	}

	public Long getProjectPhaseId() {
		return projectPhaseId;
	}

	public void setProjectPhaseId(Long projectPhaseId) {
		this.projectPhaseId = projectPhaseId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ProjectPhaseAttachment{" +
			"id=" + id +
			", resourceAttachmentId=" + resourceAttachmentId +
			", projectPhaseId=" + projectPhaseId +
			", projectId=" + projectId +
			", desc=" + desc +
			"}";
	}
}

package com.proflow.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 项目合同资源表
 * </p>
 *
 * @author Leonid
 * @since 2018-08-06
 */
public class ProjectContractResource extends Model<ProjectContractResource> {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 资源名称
     */
	private String name;
	private Long resourceAttachmentId;
	private Long projectContractId;
    /**
     * 排序
     */
	private Integer sort;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getResourceAttachmentId() {
		return resourceAttachmentId;
	}

	public void setResourceAttachmentId(Long resourceAttachmentId) {
		this.resourceAttachmentId = resourceAttachmentId;
	}

	public Long getProjectContractId() {
		return projectContractId;
	}

	public void setProjectContractId(Long projectContractId) {
		this.projectContractId = projectContractId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ProjectContractResource{" +
			"id=" + id +
			", name=" + name +
			", resourceAttachmentId=" + resourceAttachmentId +
			", projectContractId=" + projectContractId +
			", sort=" + sort +
			"}";
	}
}

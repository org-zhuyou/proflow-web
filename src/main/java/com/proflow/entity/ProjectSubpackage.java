package com.proflow.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 项目分包阶段表
 * </p>
 *
 * @author Leonid
 * @since 2018-08-06
 */
public class ProjectSubpackage extends Model<ProjectSubpackage> {

    private static final long serialVersionUID = 1L;

	private Long id;
	private Long projectId;
    /**
     * 合同id
     */
	private Long projectContractId;
    /**
     * 分包企业名称
     */
	private String orgName;
    /**
     * 分包类型 1 土建（基础） 2 土建（主体） 3 装修 4 基电
     */
	private Integer type;
    /**
     * 创建人
     */
	private Long createUser;
	private Date createTime;

	public static final Integer JICHU_TUJIAN = 1;
	public static final Integer ZHUTI_TUJIAN = 2;
	public static final Integer ZHUANGXIU = 3;
	public static final Integer JIDIAN = 4;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getProjectContractId() {
		return projectContractId;
	}

	public void setProjectContractId(Long projectContractId) {
		this.projectContractId = projectContractId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ProjectSubpackage{" +
			"id=" + id +
			", projectId=" + projectId +
			", projectContractId=" + projectContractId +
			", orgName=" + orgName +
			", type=" + type +
			", createUser=" + createUser +
			", createTime=" + createTime +
			"}";
	}
}

package com.proflow.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 项目节点表
 * </p>
 *
 * @author Leonid
 * @since 2018-08-06
 */
public class ProjectPhase extends Model<ProjectPhase> {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 节点名称
     */
	private String name;
    /**
     * 项目id
     */
	private Long projectId;
    /**
     * 完成情况 0-100
     */
	private Integer complete;
    /**
     * 工期/天
     */
	private Integer timeLimit;
    /**
     * 开始时间
     */
	private Date startDate;
    /**
     * 项目节点描述
     */
	private String desc;
    /**
     * 备注
     */
	private String remark;
    /**
     * 结束时间
     */
	private Date endDate;
    /**
     * 创建人
     */
	private Long createUser;
	private Date createTime;


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

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Integer getComplete() {
		return complete;
	}

	public void setComplete(Integer complete) {
		this.complete = complete;
	}

	public Integer getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
		return "ProjectPhase{" +
			"id=" + id +
			", name=" + name +
			", projectId=" + projectId +
			", complete=" + complete +
			", timeLimit=" + timeLimit +
			", startDate=" + startDate +
			", desc=" + desc +
			", remark=" + remark +
			", endDate=" + endDate +
			", createUser=" + createUser +
			", createTime=" + createTime +
			"}";
	}
}

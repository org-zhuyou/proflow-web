package com.proflow.entity;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 项目节点款项
 * </p>
 *
 * @author Leonid
 * @since 2018-08-06
 */
public class ProjectPhaseFund extends Model<ProjectPhaseFund> {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 项目节点id
     */
	private Long projectPhaseId;
    /**
     * 项目id
     */
	private Long projectId;
    /**
     * 款项名称
     */
	private String name;
    /**
     * 金额
     */
	private BigDecimal amount;
    /**
     * 实际金额
     */
	private BigDecimal actualAmount;
    /**
     * 完成情况 0-100
     */
	private Integer complete;
    /**
     * 状态 0 财务未确认 1 财务已确认
     */
	private Integer status;
    /**
     * 备注
     */
	private String remark;
    /**
     * 确认人
     */
	private Long validator;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}

	public Integer getComplete() {
		return complete;
	}

	public void setComplete(Integer complete) {
		this.complete = complete;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getValidator() {
		return validator;
	}

	public void setValidator(Long validator) {
		this.validator = validator;
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
		return "ProjectPhaseFund{" +
			"id=" + id +
			", projectPhaseId=" + projectPhaseId +
			", projectId=" + projectId +
			", name=" + name +
			", amount=" + amount +
			", actualAmount=" + actualAmount +
			", complete=" + complete +
			", status=" + status +
			", remark=" + remark +
			", validator=" + validator +
			", createUser=" + createUser +
			", createTime=" + createTime +
			"}";
	}
}

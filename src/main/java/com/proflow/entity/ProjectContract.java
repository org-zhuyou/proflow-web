package com.proflow.entity;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 项目合同
 * </p>
 *
 * @author Leonid
 * @since 2018-08-06
 */
public class ProjectContract extends Model<ProjectContract> {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 合同名称
     */
	private String name;
    /**
     * 合同类型 1 总包合同 2 分包合同
     */
	private Integer type;
    /**
     * 合同编号
     */
	private String code;
    /**
     * 工期/天
     */
	private Integer timeLimit;
    /**
     * 开始时间
     */
	private Date startDate;
    /**
     * 结束时间
     */
	private Date endDate;
    /**
     * 项目总金额
     */
	private BigDecimal totalAmount;
    /**
     * 项目地点
     */
	private String address;
    /**
     * 甲方
     */
	private String partyA;
    /**
     * 乙方
     */
	private String partyB;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPartyA() {
		return partyA;
	}

	public void setPartyA(String partyA) {
		this.partyA = partyA;
	}

	public String getPartyB() {
		return partyB;
	}

	public void setPartyB(String partyB) {
		this.partyB = partyB;
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
		return "ProjectContract{" +
			"id=" + id +
			", name=" + name +
			", type=" + type +
			", code=" + code +
			", timeLimit=" + timeLimit +
			", startDate=" + startDate +
			", endDate=" + endDate +
			", totalAmount=" + totalAmount +
			", address=" + address +
			", partyA=" + partyA +
			", partyB=" + partyB +
			", createUser=" + createUser +
			", createTime=" + createTime +
			"}";
	}
}

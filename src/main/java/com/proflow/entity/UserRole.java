package com.proflow.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 用户角色表
 * </p>
 *
 * @author Leonid
 * @since 2018-08-06
 */
public class UserRole extends Model<UserRole> {

    private static final long serialVersionUID = 1L;

	private Long id;
	private Long userId;
	private Long roleId;
	private Date createTime;

	public UserRole() {}

	public UserRole(Long userId, Long roleId) {
		this.userId = userId;
		this.roleId = roleId;
		this.createTime = new Date();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
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
		return "UserRole{" +
			"id=" + id +
			", userId=" + userId +
			", roleId=" + roleId +
			", createTime=" + createTime +
			"}";
	}
}

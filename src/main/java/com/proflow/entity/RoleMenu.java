package com.proflow.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 角色菜单表
 * </p>
 *
 * @author Leonid
 * @since 2018-08-06
 */
public class RoleMenu extends Model<RoleMenu> {

    private static final long serialVersionUID = 1L;

	private Long id;
	private Long roleId;
	private Long menuId;
	private Date createTime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
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
		return "RoleMenu{" +
			"id=" + id +
			", roleId=" + roleId +
			", menuId=" + menuId +
			", createTime=" + createTime +
			"}";
	}
}

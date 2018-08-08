package com.proflow.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author Leonid
 * @since 2018-08-06
 */
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 角色名称
     */
	private String name;
    /**
     * 角色code
     */
	private String code;
    /**
     * 描述
     */
	private String desc;
	private Date createTime;
	private Date modifyTime;


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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Role{" +
			"id=" + id +
			", name=" + name +
			", code=" + code +
			", desc=" + desc +
			", createTime=" + createTime +
			", modifyTime=" + modifyTime +
			"}";
	}
}

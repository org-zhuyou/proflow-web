package com.proflow.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author Leonid
 * @since 2018-08-06
 */
public class Menu extends Model<Menu> {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 父id
     */
	private Long pid;
    /**
     * 菜单名称
     */
	private String name;

	private String path;
    /**
     * 菜单code
     */
	private String code;
    /**
     * 菜单类型
     */
	private Integer type;
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

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Menu{" +
			"id=" + id +
			", pid=" + pid +
			", name=" + name +
			", code=" + code +
			", type=" + type +
			", desc=" + desc +
			"}";
	}
}

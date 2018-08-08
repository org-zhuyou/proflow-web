package com.proflow.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 资源附件表
 * </p>
 *
 * @author Leonid
 * @since 2018-08-06
 */
public class ResourceAttachment extends Model<ResourceAttachment> {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 资源名称
     */
	private String name;
    /**
     * 资源url
     */
	private String url;
    /**
     * 文件大小
     */
	private Integer size;
    /**
     * local资源路径
     */
	private String filePath;
    /**
     * 文件后缀
     */
	private String suffix;
    /**
     * 资源类型
     */
	private Integer type;


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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ResourceAttachment{" +
			"id=" + id +
			", name=" + name +
			", url=" + url +
			", size=" + size +
			", filePath=" + filePath +
			", suffix=" + suffix +
			", type=" + type +
			"}";
	}
}

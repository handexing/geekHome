package com.geekhome.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.geekhome.common.utils.CustomDateSerializer;

@Entity
@Table(name = "MENU")
public class Menu implements Serializable {

	private static final long serialVersionUID = 6423970748458325777L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "TYPE")
	private String type;
	@Column(name = "URL")
	private String url;
	@Column(name = "CODE")
	private String code;
	@Column(name = "PARENT_ID")
	private Long parentId;
	@Column(name = "PARENT_IDS")
	private String parentIds;
	@Column(name = "CHILD_CNT")
	private Integer childCnt;
	@Column(name = "SORT")
	private Integer sort;
	@JsonSerialize(using = CustomDateSerializer.class)
	@Column(name = "CREATE_TIME")
	private Date createTime;
	@JsonSerialize(using = CustomDateSerializer.class)
	@Column(name = "UPDATE_TIME")
	private Date updateTime;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public Integer getChildCnt() {
		return childCnt;
	}

	public void setChildCnt(Integer childCnt) {
		this.childCnt = childCnt;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Menu() {
		super();
	}

	public Menu(Long id, String name, String type, String url, String code, Long parentId, String parentIds,
			Integer childCnt, Integer sort, Date createTime, Date updateTime) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.url = url;
		this.code = code;
		this.parentId = parentId;
		this.parentIds = parentIds;
		this.childCnt = childCnt;
		this.sort = sort;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", name=" + name + ", type=" + type + ", url=" + url + ", code=" + code
				+ ", parentId=" + parentId + ", parentIds=" + parentIds + ", childCnt=" + childCnt + ", sort=" + sort
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

}

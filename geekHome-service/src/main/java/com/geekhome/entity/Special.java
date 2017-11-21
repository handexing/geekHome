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
@Table(name = "SPECIAL")
public class Special implements Serializable {

	private static final long serialVersionUID = -3836589576246776724L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	@Column(name = "USER_ID")
	private Long userId;
	@Column(name = "NAME")
	private String name;
	@Column(name = "SUBTITLE")
	private String subtitle;
	@Column(name = "IMG_PATH")
	private String imgPath;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
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

	public Special(Long id, Long userId, String name, String subtitle, String imgPath, Integer sort, Date createTime,
			Date updateTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.subtitle = subtitle;
		this.imgPath = imgPath;
		this.sort = sort;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public Special() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Special [id=" + id + ", userId=" + userId + ", name=" + name + ", subtitle=" + subtitle + ", imgPath="
				+ imgPath + ", sort=" + sort + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

}

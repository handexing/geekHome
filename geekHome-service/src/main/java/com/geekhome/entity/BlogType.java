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
@Table(name = "BLOG_TYPE")
public class BlogType implements Serializable {

	/**
	 * 默认开启
	 */
	public static final Integer LABEL_STATE_DEFAULT = 1;
	/**
	 * 关闭
	 */
	public static final Integer LABEL_STATE_CLOSE = 0;

	private static final long serialVersionUID = 2159291991132749505L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "STATUS")
	private Integer status;
	@Column(name = "USER_ID")
	private Long userId;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public BlogType(Long id, String name, Integer status, Long userId, Date createTime,
			Date updateTime) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.userId = userId;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public BlogType() {
		super();
	}

	@Override
	public String toString() {
		return "BlogType [id=" + id + ", name=" + name  + ", status="
				+ status + ", userId=" + userId + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

}

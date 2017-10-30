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
@Table(name = "OPEN_SOURCE_LABEL")
public class OpenSourceLabel implements Serializable {

	/**
	 * 默认开启
	 */
	public static final Integer OPEN_SOURCE_LABEL_STATE_DEFAULT = 1;
	/**
	 * 关闭
	 */
	public static final Integer OPEN_SOURCE_LABEL_STATE_CLOSE = 0;

	private static final long serialVersionUID = 2159291991132749505L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "SORT")
	private Integer sort;
	@Column(name = "STATUS")
	private Integer status;
	@JsonSerialize(using = CustomDateSerializer.class)
	@Column(name = "CREATE_TIME")
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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public OpenSourceLabel() {
		super();
	}

	public OpenSourceLabel(Long id, String name, Integer sort, Integer status, Date createTime) {
		super();
		this.id = id;
		this.name = name;
		this.sort = sort;
		this.status = status;
		this.createTime = createTime;
	}

}

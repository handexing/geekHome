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

/**
 * @Description: 标签
 * @author handx
 * @date 2017年10月3日 下午2:57:46
 * @version V1.0
 */
@Entity
@Table(name = "LABEL")
public class Label implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2159291991132749505L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	@Column(name = "LABLE_NAME")
	private String lableName;
	@Column(name = "PARENT_ID")
	private Long parentId;
	@Column(name = "SORT")
	private Integer sort;
	@Column(name = "TYPE")
	private Integer type;
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

	public String getLableName() {
		return lableName;
	}

	public void setLableName(String lableName) {
		this.lableName = lableName;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public Label() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Label(Long id, String lableName, Long parentId, Integer sort, Integer type, Date createTime,
			Date updateTime) {
		super();
		this.id = id;
		this.lableName = lableName;
		this.parentId = parentId;
		this.sort = sort;
		this.type = type;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "Label [id=" + id + ", lableName=" + lableName + ", parentId=" + parentId + ", sort=" + sort + ", type="
				+ type + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

}

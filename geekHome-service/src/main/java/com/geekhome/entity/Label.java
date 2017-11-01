package com.geekhome.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	 * 标签
	 */
	public static final Integer LABLE = 1;
	/**
	 * 开源
	 */
	public static final Integer OPEN_SOURCE = 2;
	/**
	 * 问与答
	 */
	public static final Integer Q_A = 3;
	
	/**
	 * 博客
	 */
	public static final Integer BLOG = 4;

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
	@Column(name = "LABLE_NAME")
	private String lableName;
	@Column(name = "PARENT_ID")
	private Long parentId;
	@Column(name = "SORT")
	private Integer sort;
	@Column(name = "TYPE")
	private Integer type;
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

	@Transient
	private List<Label> childs;
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Label> getChilds() {
		return childs;
	}

	public void setChilds(List<Label> childs) {
		this.childs = childs;
	}

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

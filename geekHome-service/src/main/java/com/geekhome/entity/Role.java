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
 * @Description: 角色
 * @author handx
 * @date 2017年9月14日 下午5:19:53
 * @version V1.0
 */
@Entity
@Table(name = "ROLE")
public class Role implements Serializable {

	private static final long serialVersionUID = -2337967383981152117L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "ROLE_DESC")
	private String roleDesc;
	@Column(name = "STATE")
	private Integer state;
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

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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

	public Role() {
		super();
	}

	public Role(Long id, String name, String roleDesc, Integer state, Date createTime, Date updateTime) {
		super();
		this.id = id;
		this.name = name;
		this.roleDesc = roleDesc;
		this.state = state;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", roleDesc=" + roleDesc + ", state=" + state + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}

}

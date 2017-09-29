package com.geekhome.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADMIN_ROLE")
public class AdminRole implements Serializable {

	private static final long serialVersionUID = 545001797592305245L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	@Column(name = "ROLE_ID")
	private Long roleId;
	@Column(name = "ADMIN_ID")
	private Long adminId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public AdminRole() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminRole(Long id, Long roleId, Long adminId) {
		super();
		this.id = id;
		this.roleId = roleId;
		this.adminId = adminId;
	}

	@Override
	public String toString() {
		return "AdminRole [id=" + id + ", roleId=" + roleId + ", adminId=" + adminId + "]";
	}

}

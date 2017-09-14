package com.geekhome.entity;

public class RoleMenu {

	private Long roleId;
	private Long menuId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public RoleMenu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoleMenu(Long roleId, Long menuId) {
		super();
		this.roleId = roleId;
		this.menuId = menuId;
	}

	@Override
	public String toString() {
		return "RoleMenu [roleId=" + roleId + ", menuId=" + menuId + "]";
	}

}

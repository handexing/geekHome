package com.geekhome.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.geekhome.common.utils.CustomDateSerializer;

/**
 * @Description: 管理员
 * @author handx
 * @date 2017年9月14日 下午5:02:45
 * @version V1.0
 */

@SqlResultSetMappings({

	@SqlResultSetMapping(name = "getAdminList",
		classes = @ConstructorResult(targetClass = Admin.class,
			columns = { @ColumnResult(name = "id", type = Long.class),
				@ColumnResult(name = "userName", type = String.class), 
				@ColumnResult(name = "password", type = String.class),
				@ColumnResult(name = "state", type = Integer.class), 
				@ColumnResult(name = "isSystem", type = Integer.class),
				@ColumnResult(name = "salt", type = String.class),
				@ColumnResult(name = "createTime", type = Date.class),
				@ColumnResult(name = "updateTime", type = Date.class),
				@ColumnResult(name = "roleName", type = String.class) })) })
@Entity
@Table(name = "ADMIN")
public class Admin implements Serializable {

	private static final long serialVersionUID = -3836589576246776724L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	@Column(name = "USER_NAME")
	private String userName;
	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "STATE")
	private Integer state;
	@Column(name = "IS_SYSTEM")
	private Integer isSystem;
	@Column(name = "SALT")
	private String salt;
	@JsonSerialize(using = CustomDateSerializer.class)
	@Column(name = "CREATE_TIME")
	private Date createTime;
	@JsonSerialize(using = CustomDateSerializer.class)
	@Column(name = "UPDATE_TIME")
	private Date updateTime;

	@Transient
	private String roleName;
	
	public Admin(Long id, String userName, String password, Integer state, Integer isSystem, String salt,
			Date createTime, Date updateTime, String roleName) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.state = state;
		this.isSystem = isSystem;
		this.salt = salt;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getCredentialsSalt() {
		return this.userName + this.salt;
	}

	public Integer getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(Integer isSystem) {
		this.isSystem = isSystem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
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

	public Admin() {
		super();
	}

	public Admin(Long id, String userName, String password, Integer state, String salt, Date createTime,
			Date updateTime) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.state = state;
		this.salt = salt;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", userName=" + userName + ", password=" + password + ", state=" + state
				+ ", isSystem=" + isSystem + ", salt=" + salt + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", roleName=" + roleName + "]";
	}


}

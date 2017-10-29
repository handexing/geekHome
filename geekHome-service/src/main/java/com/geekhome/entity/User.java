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
 * @Description: 用户
 * @author handx
 * @date 2017年10月9日 上午10:16:18
 * @version V1.0
 */
@Entity
@Table(name = "USER")
public class User implements Serializable {

	private static final long serialVersionUID = 5258074137488631269L;

	/**
	 * 性别默认
	 */
	public static final Integer USER_SEX_DEFAULT = 0;
	/**
	 * 男性
	 */
	public static final Integer USER_SEX_MALE = 1;
	/**
	 * 女性
	 */
	public static final Integer USER_SEX_FEMALE = 2;
	/**
	 * 默认开启
	 */
	public static final Integer USER_STATE_DEFAULT = 1;
	/**
	 * 关闭
	 */
	public static final Integer USER_STATE_CLOSE = 0;

	/**
	 * 盐
	 */
	public static final String SALT = "geekHome";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	@Column(name = "USER_NAME")
	private String userName;
	@Column(name = "SEX")
	private Integer sex;
	@Column(name = "BIRTHDAY")
	private Date birthday;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "PHONE")
	private String phone;
	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "BRIEF")
	private String brief;
	@Column(name = "HEAD_IMG_URL")
	private String headImgUrl;
	@Column(name = "STATE")
	private Integer state;
	@JsonSerialize(using = CustomDateSerializer.class)
	@Column(name = "CREATE_TIME")
	private Date createTime;
	@JsonSerialize(using = CustomDateSerializer.class)
	@Column(name = "UPDATE_TIME")
	private Date updateTime;
	@Column(name = "COMPANY")
	private String company;
	@Column(name = "ADDRESS")
	private String address;
	@Column(name = "GIT_HUB_URL")
	private String gitHubUrl;
	@Column(name = "WEB_SITE_URL")
	private String webSiteUrl;

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGitHubUrl() {
		return gitHubUrl;
	}

	public void setGitHubUrl(String gitHubUrl) {
		this.gitHubUrl = gitHubUrl;
	}

	public String getWebSiteUrl() {
		return webSiteUrl;
	}

	public void setWebSiteUrl(String webSiteUrl) {
		this.webSiteUrl = webSiteUrl;
	}

	public String getCredentialsSalt() {
		return userName + "geekHome";
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

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
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

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long id, String userName, Integer sex, Date birthday, String email, String phone, String password,
			String brief, String headImgUrl, Integer state, Date createTime, Date updateTime) {
		super();
		this.id = id;
		this.userName = userName;
		this.sex = sex;
		this.birthday = birthday;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.brief = brief;
		this.headImgUrl = headImgUrl;
		this.state = state;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", sex=" + sex + ", birthday=" + birthday + ", email="
				+ email + ", phone=" + phone + ", password=" + password + ", brief=" + brief + ", headImgUrl="
				+ headImgUrl + ", state=" + state + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

}

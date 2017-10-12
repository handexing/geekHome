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
 * @Description: 评论
 * @author handx
 * @date 2017年10月12日 下午2:51:59
 * @version V1.0
 */
@Entity
@Table(name = "COMMENT")
public class Comment implements Serializable {

	private static final long serialVersionUID = 2882417242489113783L;

	/**
	 * 评论类型：开源
	 */
	public static final Integer COMMENT_THEME_OPEN_SOURCE = 1;
	/**
	 * 评论类型：问与答
	 */
	public static final Integer COMMENT_THEME_QUESTION_ANSWERS = 2;
	/**
	 * 评论类型：博客
	 */
	public static final Integer COMMENT_THEME_BLOG = 3;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	@Column(name = "USER_ID")
	private Long userId;
	@Column(name = "THEME_ID")
	private Long themeId;
	@Column(name = "CONTENT")
	private String content;
	@JsonSerialize(using = CustomDateSerializer.class)
	@Column(name = "CREATE_TIME")
	private Date createTime;

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

	public Long getThemeId() {
		return themeId;
	}

	public void setThemeId(Long themeId) {
		this.themeId = themeId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Comment() {
		super();
	}

	public Comment(Long id, Long userId, Long themeId, String content, Date createTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.themeId = themeId;
		this.content = content;
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", userId=" + userId + ", themeId=" + themeId + ", content=" + content
				+ ", createTime=" + createTime + "]";
	}

}

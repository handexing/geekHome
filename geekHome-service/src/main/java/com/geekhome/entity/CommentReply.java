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
 * @Description: 评论回复
 * @author handx
 * @date 2017年10月12日 下午2:44:24
 * @version V1.0
 */
@Entity
@Table(name = "COMMENT_REPLY")
public class CommentReply implements Serializable {

	private static final long serialVersionUID = 8974015015575428341L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	@Column(name = "REPLY_USER_ID")
	private Long replyUserId;
	@Column(name = "USER_ID")
	private Long userId;
	@Column(name = "CONTENT")
	private String content;
	@JsonSerialize(using = CustomDateSerializer.class)
	@Column(name = "CREATE_TIME")
	private Date createTime;
	@Column(name = "TYPE")
	private Integer type;

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

	public CommentReply() {
		super();
	}

	public Long getReplyUserId() {
		return replyUserId;
	}

	public void setReplyUserId(Long replyUserId) {
		this.replyUserId = replyUserId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "CommentReply [id=" + id + ", replyUserId=" + replyUserId + ", userId=" + userId + ", content=" + content
				+ ", createTime=" + createTime + ", type=" + type + "]";
	}

	public CommentReply(Long id, Long replyUserId, Long userId, String content, Date createTime, Integer type) {
		super();
		this.id = id;
		this.replyUserId = replyUserId;
		this.userId = userId;
		this.content = content;
		this.createTime = createTime;
		this.type = type;
	}

}

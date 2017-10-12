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
	@Column(name = "REPLY_IDS")
	private String replyIds;
	@Column(name = "USER_ID")
	private Long userId;
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

	public String getReplyIds() {
		return replyIds;
	}

	public void setReplyIds(String replyIds) {
		this.replyIds = replyIds;
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

	public CommentReply(Long id, String replyIds, Long userId, String content, Date createTime) {
		super();
		this.id = id;
		this.replyIds = replyIds;
		this.userId = userId;
		this.content = content;
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "CommentReply [id=" + id + ", replyIds=" + replyIds + ", userId=" + userId + ", content=" + content
				+ ", createTime=" + createTime + "]";
	}

}

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
 * @Description: 问与答
 * @author handx
 * @date 2017年10月12日 下午2:44:24
 * @version V1.0
 */
@Entity
@Table(name = "QUESTION_ANSWERS")
public class QuestionAnswers implements Serializable {

	private static final long serialVersionUID = 562199224417013272L;
	
	/**
	 * 默认开启
	 */
	public static final Integer QUESTION_ANSWERS_STATE_DEFAULT = 1;
	/**
	 * 关闭
	 */
	public static final Integer QUESTION_ANSWERS_STATE_CLOSE = 0;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	@Column(name = "USER_ID")
	private Long userId;
	@Column(name = "LABLE_ID")
	private Long lableId;
	@Column(name = "TITLE")
	private String title;
	@Column(name = "CONTENT")
	private String content;
	@Column(name = "STATUS")
	private Integer status;
	@Column(name = "COLLECT_COUNT")
	private Integer collectCount;
	@Column(name = "BROWSE_COUNT")
	private Integer browseCount;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getLableId() {
		return lableId;
	}

	public void setLableId(Long lableId) {
		this.lableId = lableId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCollectCount() {
		return collectCount;
	}

	public void setCollectCount(Integer collectCount) {
		this.collectCount = collectCount;
	}

	public Integer getBrowseCount() {
		return browseCount;
	}

	public void setBrowseCount(Integer browseCount) {
		this.browseCount = browseCount;
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

	public QuestionAnswers() {
		super();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public QuestionAnswers(Long id, Long userId, Long lableId, String title, String content, Integer status,
			Integer collectCount, Integer browseCount, Date createTime, Date updateTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.lableId = lableId;
		this.title = title;
		this.content = content;
		this.status = status;
		this.collectCount = collectCount;
		this.browseCount = browseCount;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "QuestionAnswers [id=" + id + ", userId=" + userId + ", lableId=" + lableId + ", title=" + title
				+ ", content=" + content + ", status=" + status + ", collectCount=" + collectCount + ", browseCount="
				+ browseCount + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

}

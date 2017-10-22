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
 * @Description: 问与答
 * @author handx
 * @date 2017年10月12日 下午2:44:24
 * @version V1.0
 */
@SqlResultSetMappings({

		@SqlResultSetMapping(name = "getQuestionAnswersList", classes = @ConstructorResult(targetClass = QuestionAnswers.class, columns = {
				@ColumnResult(name = "id", type = Long.class), @ColumnResult(name = "userId", type = Long.class),
				@ColumnResult(name = "labelId", type = Long.class), @ColumnResult(name = "title", type = String.class),
				@ColumnResult(name = "collectCount", type = Integer.class),
				@ColumnResult(name = "browseCount", type = Integer.class),
				@ColumnResult(name = "createTime", type = Date.class),
				@ColumnResult(name = "updateTime", type = Date.class),
				@ColumnResult(name = "labelName", type = String.class),
				@ColumnResult(name = "userName", type = String.class),
				@ColumnResult(name = "headImgUrl", type = String.class) })) })
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

	/**
	 * 草稿
	 */
	public static final Integer QUESTION_ANSWERS_STATE_DRAFT = 2;

	/**
	 * 发布
	 */
	public static final Integer QUESTION_ANSWERS_STATE_RELEASE = 3;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	@Column(name = "USER_ID")
	private Long userId;
	@Column(name = "LABEL_ID")
	private Long labelId;
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

	@Transient
	private String labelName;
	@Transient
	private String userName;
	@Transient
	private String headImgUrl;

	public QuestionAnswers(Long id, Long userId, Long labelId, String title, Integer collectCount, Integer browseCount,
			Date createTime, Date updateTime, String labelName, String userName, String headImgUrl) {
		super();
		this.id = id;
		this.userId = userId;
		this.labelId = labelId;
		this.title = title;
		this.collectCount = collectCount;
		this.browseCount = browseCount;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.labelName = labelName;
		this.userName = userName;
		this.headImgUrl = headImgUrl;
	}

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

	public Long getLabelId() {
		return labelId;
	}

	public void setLabelId(Long labelId) {
		this.labelId = labelId;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public QuestionAnswers(Long id, Long userId, Long labelId, String title, String content, Integer status,
			Integer collectCount, Integer browseCount, Date createTime, Date updateTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.labelId = labelId;
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
		return "QuestionAnswers [id=" + id + ", userId=" + userId + ", labelId=" + labelId + ", title=" + title
				+ ", content=" + content + ", status=" + status + ", collectCount=" + collectCount + ", browseCount="
				+ browseCount + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

}

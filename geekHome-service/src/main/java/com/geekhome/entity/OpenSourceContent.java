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

@SqlResultSetMappings({

		@SqlResultSetMapping(name = "getOpenSourceListByLabelId", classes = @ConstructorResult(targetClass = OpenSourceContent.class, columns = {
				@ColumnResult(name = "id", type = Long.class), 
				@ColumnResult(name = "labelId", type = Long.class),
				@ColumnResult(name = "title", type = String.class),
				@ColumnResult(name = "subtitle", type = String.class),
				@ColumnResult(name = "content", type = String.class),
				@ColumnResult(name = "collectCount", type = Integer.class),
				@ColumnResult(name = "browseCount", type = Integer.class),
				@ColumnResult(name = "commentCnt", type = Integer.class),
				@ColumnResult(name = "bannerImg", type = String.class),
				@ColumnResult(name = "createTime", type = Date.class),
				@ColumnResult(name = "updateTime", type = Date.class) })) })
@Entity
@Table(name = "OPEN_SOURCE_CONTENT")
public class OpenSourceContent implements Serializable {

	private static final long serialVersionUID = 562199224417013272L;

	/**
	 * 默认开启
	 */
	public static final Integer OPEN_SOURCE_CONTENT_STATE_DEFAULT = 1;
	/**
	 * 关闭
	 */
	public static final Integer OPEN_SOURCE_CONTENT_STATE_CLOSE = 0;

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
	@Column(name = "SUBTITLE")
	private String subtitle;
	@Column(name = "BANNER_IMG")
	private String bannerImg;
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
	private Integer commentCnt;

	public Integer getCommentCnt() {
		return commentCnt;
	}

	public void setCommentCnt(Integer commentCnt) {
		this.commentCnt = commentCnt;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getBannerImg() {
		return bannerImg;
	}

	public void setBannerImg(String bannerImg) {
		this.bannerImg = bannerImg;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLabelId() {
		return labelId;
	}

	public void setLabelId(Long labelId) {
		this.labelId = labelId;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public OpenSourceContent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OpenSourceContent(Long id, Long labelId, String title, String content, String subtitle, String bannerImg,
			Integer status, Integer collectCount, Integer browseCount, Date createTime, Date updateTime) {
		super();
		this.id = id;
		this.labelId = labelId;
		this.title = title;
		this.content = content;
		this.subtitle = subtitle;
		this.bannerImg = bannerImg;
		this.status = status;
		this.collectCount = collectCount;
		this.browseCount = browseCount;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public OpenSourceContent(Long id, Long labelId, String title, String subtitle, String content, Integer collectCount,
			Integer browseCount, Integer commentCnt, String bannerImg, Date createTime, Date updateTime) {
		super();
		this.id = id;
		this.labelId = labelId;
		this.title = title;
		this.subtitle = subtitle;
		this.content = content;
		this.collectCount = collectCount;
		this.browseCount = browseCount;
		this.commentCnt = commentCnt;
		this.bannerImg = bannerImg;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "OpenSourceContent [id=" + id + ", labelId=" + labelId + ", title=" + title + ", content=" + content
				+ ", subtitle=" + subtitle + ", bannerImg=" + bannerImg + ", status=" + status + ", collectCount="
				+ collectCount + ", browseCount=" + browseCount + ", createTime=" + createTime + ", updateTime="
				+ updateTime + "]";
	}

}

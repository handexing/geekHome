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

		@SqlResultSetMapping(name = "getBlogList", classes = @ConstructorResult(targetClass = Blog.class, columns = {
				@ColumnResult(name = "id", type = Long.class),
				@ColumnResult(name = "typeId", type = Long.class),
				@ColumnResult(name = "title", type = String.class),
				@ColumnResult(name = "subtitle", type = String.class),
				@ColumnResult(name = "bannerImg", type = String.class),
				@ColumnResult(name = "status", type = Integer.class),
				@ColumnResult(name = "collectCount", type = Integer.class),
				@ColumnResult(name = "browseCount", type = Integer.class),
				@ColumnResult(name = "createTime", type = Date.class),
				@ColumnResult(name = "updateTime", type = Date.class),
				@ColumnResult(name = "commentCnt", type = Integer.class) })) })

@Entity
@Table(name = "BLOG")
public class Blog implements Serializable {

	private static final long serialVersionUID = 562199224417013272L;

	/**
	 * 默认开启
	 */
	public static final Integer BLOG_STATE_DEFAULT = 1;
	/**
	 * 关闭
	 */
	public static final Integer BLOG_STATE_CLOSE = 0;

	/**
	 * 草稿
	 */
	public static final Integer BLOG_STATE_DRAFT = 2;

	/**
	 * 发布
	 */
	public static final Integer BLOG_STATE_RELEASE = 3;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	@Column(name = "TYPE_ID")
	private Long typeId;
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


	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
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

	public Blog() {
		super();
	}

	public Blog(Long id, Long typeId, String title, String content, String subtitle, String bannerImg, Integer status,
			Integer collectCount, Integer browseCount, Date createTime, Date updateTime) {
		super();
		this.id = id;
		this.typeId = typeId;
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

	public Blog(Long id, Long typeId, String title, String subtitle, String bannerImg, Integer status,
			Integer collectCount, Integer browseCount, Date createTime, Date updateTime, Integer commentCnt) {
		super();
		this.id = id;
		this.typeId = typeId;
		this.title = title;
		this.subtitle = subtitle;
		this.bannerImg = bannerImg;
		this.status = status;
		this.collectCount = collectCount;
		this.browseCount = browseCount;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.commentCnt = commentCnt;
	}

	@Override
	public String toString() {
		return "Blog [id=" + id + ", typeId=" + typeId + ", title=" + title + ", content=" + content
				+ ", subtitle=" + subtitle + ", bannerImg=" + bannerImg + ", status=" + status + ", collectCount="
				+ collectCount + ", browseCount=" + browseCount + ", createTime=" + createTime + ", updateTime="
				+ updateTime + "]";
	}

}

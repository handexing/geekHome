package com.geekhome.entity.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.geekhome.common.utils.PageUtil;
import com.geekhome.entity.Blog;
import com.geekhome.entity.dao.BlogDao;

@Service
public class BlogService {

	@Autowired
	BlogDao blogDao;
	@Autowired
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public Page<Blog> findBlogByUserIdAndLabelIdList(Long userId, Long typeId, Integer page, int rows) {
		int firstRecord = PageUtil.calcPage(page) * rows;
		String sql = "SELECT o.ID id,o.TYPE_ID typeId,o.TITLE title,o.SUBTITLE subtitle,o.BANNER_IMG bannerImg,o.`STATUS` status,o.COLLECT_COUNT collectCount,o.BROWSE_COUNT browseCount,o.CREATE_TIME createTime,"
				+ "o.UPDATE_TIME updateTime,(SELECT COUNT(1) FROM COMMENT WHERE THEME_ID = o.ID AND TYPE=3) as commentCnt"
				+ " FROM blog AS o LEFT JOIN BLOG_TYPE AS l ON o.TYPE_ID = l.ID"
				+ " WHERE TYPE_ID=:typeId AND l.USER_ID=:userId ORDER BY o.CREATE_TIME DESC";
		List<Blog> list = entityManager.createNativeQuery(sql, "getBlogList").setParameter("typeId", typeId)
				.setParameter("userId", userId).setFirstResult(firstRecord).setMaxResults(rows).getResultList();
		int total = blogDao.getBlogByUserIdCnt(userId);
		Pageable pageable = new PageRequest(page, rows);
		Page<Blog> pages = new PageImpl<>(list, pageable, total);
		return pages;
	}

	public void saveBlog(Blog blog) {
		if (blog.getId() != null) {
			//TODO 用户修改
		}else {
			blog.setBrowseCount(0);
			blog.setCollectCount(0);
			blog.setCreateTime(new Date());
			blogDao.save(blog);
		}
	}

	@SuppressWarnings("unchecked")
	public Blog getBlogById(Long id) {
		final String sql = "SELECT b.ID id,b.TYPE_ID typeId,b.TITLE title,B.SUBTITLE subTitle,b.CONTENT content,b.COLLECT_COUNT collectCount,b.BROWSE_COUNT browseCount,b.BANNER_IMG bannerImg,b.CREATE_TIME createTime,"
				+ "b.UPDATE_TIME updateTime,t.`NAME` typeName,u.USER_NAME userName,u.HEAD_IMG_URL headImgUrl,u.ID userId"
				+ " FROM blog AS b LEFT JOIN blog_type AS t ON t.ID = b.TYPE_ID"
				+ " LEFT JOIN user AS u ON u.ID = t.USER_ID WHERE b.ID=:id";
		final List<Blog> list = entityManager.createNativeQuery(sql, "getBlogById")
				.setParameter("id", id).getResultList();
		return list.get(0);
	}

}

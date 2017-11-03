package com.geekhome.entity.service;

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
	public Page<Blog> findBlogByUserIdAndLabelIdList(Long userId,Long labelId, Integer page, int rows) {
		int firstRecord = PageUtil.calcPage(page) * rows;
		String sql = "SELECT o.ID id,o.LABEL_ID labelId,o.TITLE title,o.SUBTITLE subtitle,o.BANNER_IMG bannerImg,o.`STATUS` status,o.COLLECT_COUNT collectCount,o.BROWSE_COUNT browseCount,o.CREATE_TIME createTime,"
				+ "o.UPDATE_TIME updateTime,(SELECT COUNT(1) FROM COMMENT WHERE THEME_ID = o.ID AND TYPE=4) as commentCnt"
				+ " FROM blog AS o LEFT JOIN LABEL AS l ON o.LABEL_ID = l.ID"
				+ " WHERE LABEL_ID=:labelId AND l.USER_ID=:userId AND o.`STATUS`=1 ORDER BY o.CREATE_TIME DESC";
		List<Blog> list = entityManager.createNativeQuery(sql, "getBlogList").setParameter("labelId", labelId).setParameter("userId", userId)
				.setFirstResult(firstRecord).setMaxResults(rows).getResultList();
		int total = blogDao.getBlogByUserIdCnt(userId);
		Pageable pageable = new PageRequest(page, rows);
		Page<Blog> pages = new PageImpl<>(list, pageable, total);
		return pages;
	}

}

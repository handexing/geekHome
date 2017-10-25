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
import com.geekhome.entity.Comment;
import com.geekhome.entity.dao.CommentDao;

@Service
public class CommentService {

	@Autowired
	CommentDao commentDao;
	@Autowired
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public Page<Comment> findCommentByLabelIdList(Long id, Integer page, int rows) {
		final String sql = "SELECT c.ID id,c.USER_ID userId,c.THEME_ID themeId,c.CONTENT content,c.CREATE_TIME createTime,u.USER_NAME userName,u.HEAD_IMG_URL headImgUrl FROM comment AS c "
				+ "LEFT JOIN USER AS u ON c.USER_ID = u.ID WHERE c.THEME_ID =:id ORDER BY c.CREATE_TIME ASC";

		final int firstRecord = PageUtil.calcPage(page) * rows;
		final List<Comment> list = entityManager.createNativeQuery(sql, "getCommentList").setParameter("id", id)
				.setFirstResult(firstRecord).setMaxResults(rows).getResultList();
		final int total = commentDao.getCommentByThemeIdCnt(id);
		Pageable pageable = new PageRequest(page, rows);
		final Page<Comment> pages = new PageImpl<>(list, pageable, total);

		return pages;
	}

}

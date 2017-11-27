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
import com.geekhome.entity.OpenSourceContent;
import com.geekhome.entity.dao.OpenSourceContentDao;

@Service
public class OpenSourceService {

	@Autowired
	OpenSourceContentDao openSourceContentDao;
	@Autowired
	EntityManager entityManager;

	public void saveOpenSource(OpenSourceContent openSource) {
		if (openSource.getId() != null) {
			// TODO 用户修改
		} else {
			openSource.setBrowseCount(0);
			openSource.setCollectCount(0);
			openSource.setCreateTime(new Date());
			openSourceContentDao.save(openSource);
		}
	}

	public Page<OpenSourceContent> getOpenSourceListByLabelId(Long labelId, Integer page, Integer rows) {

		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT o.ID id,o.LABEL_ID labelId,o.TITLE title,o.SUBTITLE subtitle,o.CONTENT content,o.COLLECT_COUNT collectCount,o.BROWSE_COUNT browseCount,")
				.append("(SELECT COUNT(1) FROM COMMENT WHERE THEME_ID = o.ID AND TYPE=1) as commentCnt,o.BANNER_IMG bannerImg,o.CREATE_TIME createTime,o.UPDATE_TIME updateTime")
				.append(" FROM OPEN_SOURCE_CONTENT AS o LEFT JOIN USER AS u ON o.USER_ID = u.ID")
				.append(" LEFT JOIN LABEL AS l ON o.LABEL_ID = l.ID WHERE LABEL_ID =:labelId ORDER BY o.CREATE_TIME DESC");

		int firstRecord = PageUtil.calcPage(page) * rows;
		@SuppressWarnings("unchecked")
		List<OpenSourceContent> list = entityManager.createNativeQuery(sql.toString(), "getOpenSourceListByLabelId")
				.setParameter("labelId", labelId).setFirstResult(firstRecord).setMaxResults(rows).getResultList();

		int total = openSourceContentDao.getOpenSourceBylabelIdCnt(labelId);

		Pageable pageable = new PageRequest(page, rows);
		Page<OpenSourceContent> pages = new PageImpl<>(list, pageable, total);

		return pages;
	}

	@SuppressWarnings("unchecked")
	public OpenSourceContent getOpenSourceDetailById(Long id) {

		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT o.ID id,o.LABEL_ID labelId,o.TITLE title,o.SUBTITLE subtitle,o.CONTENT content,o.COLLECT_COUNT collectCount,o.BROWSE_COUNT browseCount,")
				.append("(SELECT COUNT(1) FROM COMMENT WHERE THEME_ID = o.ID AND TYPE=1) as commentCnt,o.BANNER_IMG bannerImg,o.CREATE_TIME createTime,o.UPDATE_TIME updateTime,")
				.append("l.LABLE_NAME lableName,u.USER_NAME userName,u.HEAD_IMG_URL headImgUrl,u.ID userId")
				.append(" FROM OPEN_SOURCE_CONTENT AS o LEFT JOIN USER AS u ON o.USER_ID = u.ID")
				.append(" LEFT JOIN LABEL AS l ON o.LABEL_ID = l.ID WHERE o.ID =:id");

		final List<OpenSourceContent> list = entityManager.createNativeQuery(sql.toString(), "getOpenSourceDetailById").setParameter("id", id)
				.getResultList();
		return list.get(0);

	}

}

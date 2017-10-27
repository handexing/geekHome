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
import com.geekhome.entity.QuestionAnswers;
import com.geekhome.entity.dao.QuestionAnswersDao;

@Service
public class QuestionAnswersService {

	@Autowired
	QuestionAnswersDao questionAnswersDao;
	@Autowired
	EntityManager entityManager;

	public void saveQuestionAnswers(QuestionAnswers questionAnswers) {
		if (questionAnswers.getId() != null) {

		} else {
			questionAnswers.setCreateTime(new Date());
			questionAnswers.setBrowseCount(0);
			questionAnswers.setCollectCount(0);
			questionAnswersDao.save(questionAnswers);
		}
	}

	@SuppressWarnings("unchecked")
	public Page<QuestionAnswers> findQuestionAnswersByLabelIdList(Long labelId, Integer page, int rows) {

		String sql = "";
		int total = 0;
		List<QuestionAnswers> list = null;
		int firstRecord = PageUtil.calcPage(page) * rows;
		
		if (labelId == 0) {//最热
			sql = "SELECT q.ID id,q.USER_ID userId,q.LABEL_ID labelId,q.TITLE title,q.COLLECT_COUNT collectCount,q.BROWSE_COUNT browseCount,q.CREATE_TIME createTime,"
					+ "q.UPDATE_TIME updateTime,l.LABLE_NAME labelName,u.USER_NAME userName,u.HEAD_IMG_URL headImgUrl,(SELECT COUNT(1) FROM COMMENT WHERE THEME_ID = q.ID) as commentCnt"
					+ " FROM QUESTION_ANSWERS AS q LEFT JOIN USER AS u ON q.USER_ID = u.ID"
					+ " LEFT JOIN LABEL AS l ON q.LABEL_ID = l.ID WHERE DATE_FORMAT(q.CREATE_TIME,'%Y-%m-%d') = DATE_FORMAT(NOW(),'%Y-%m-%d') ORDER BY commentCnt DESC";
			list = entityManager.createNativeQuery(sql, "getQuestionAnswersList")
					.setFirstResult(firstRecord).setMaxResults(rows).getResultList();
			total = questionAnswersDao.getQuestionAnswersByToDayCnt();
		} else {//正常
			sql = "SELECT q.ID id,q.USER_ID userId,q.LABEL_ID labelId,q.TITLE title,q.COLLECT_COUNT collectCount,q.BROWSE_COUNT browseCount,q.CREATE_TIME createTime,"
					+ "q.UPDATE_TIME updateTime,l.LABLE_NAME labelName,u.USER_NAME userName,u.HEAD_IMG_URL headImgUrl,(SELECT COUNT(1) FROM COMMENT WHERE THEME_ID = q.ID) as commentCnt"
					+ " FROM QUESTION_ANSWERS AS q LEFT JOIN USER AS u ON q.USER_ID = u.ID"
					+ " LEFT JOIN LABEL AS l ON q.LABEL_ID = l.ID WHERE LABEL_ID IN(SELECT ID FROM LABEL WHERE PARENT_ID=:labelId UNION SELECT "
					+ labelId + ") ORDER BY q.CREATE_TIME DESC";
			list = entityManager.createNativeQuery(sql, "getQuestionAnswersList")
					.setParameter("labelId", labelId).setFirstResult(firstRecord).setMaxResults(rows).getResultList();
			total = questionAnswersDao.getQuestionAnswersByLabelIdCnt(labelId);
		}
		
		Pageable pageable = new PageRequest(page, rows);
		Page<QuestionAnswers> pages = new PageImpl<>(list, pageable, total);

		return pages;
	}

	@SuppressWarnings("unchecked")
	public QuestionAnswers getQuestionAnswersById(Long id) {
		final String sql = "SELECT q.ID id,q.USER_ID userId,q.LABEL_ID labelId,q.TITLE title,q.CONTENT content,q.COLLECT_COUNT collectCount,q.BROWSE_COUNT browseCount,q.CREATE_TIME createTime,"
				+ "q.UPDATE_TIME updateTime,l.LABLE_NAME labelName,u.USER_NAME userName,u.HEAD_IMG_URL headImgUrl"
				+ " FROM QUESTION_ANSWERS AS q LEFT JOIN USER AS u ON q.USER_ID = u.ID"
				+ " LEFT JOIN LABEL AS l ON q.LABEL_ID = l.ID WHERE q.ID=:id";
		final List<QuestionAnswers> list = entityManager.createNativeQuery(sql, "getQuestionAnswersById")
				.setParameter("id", id).getResultList();
		return list.get(0);
	}

}

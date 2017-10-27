package com.geekhome.entity.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.geekhome.entity.QuestionAnswers;

public interface QuestionAnswersDao extends JpaRepository<QuestionAnswers, Long>{

	@Query(nativeQuery = true, value = "SELECT COUNT(1) FROM QUESTION_ANSWERS WHERE LABEL_ID IN(select ID from label WHERE PARENT_ID=:labelId UNION SELECT :labelId)")
	int getQuestionAnswersByLabelIdCnt(@Param("labelId") Long labelId);

	@Query(nativeQuery = true, value = "SELECT COUNT(1) FROM QUESTION_ANSWERS WHERE DATE_FORMAT(CREATE_TIME,'%Y-%m-%d') = DATE_FORMAT(NOW(),'%Y-%m-%d')")
	int getQuestionAnswersByToDayCnt();

}

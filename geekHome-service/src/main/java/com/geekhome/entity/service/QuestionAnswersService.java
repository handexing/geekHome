package com.geekhome.entity.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekhome.entity.QuestionAnswers;
import com.geekhome.entity.dao.QuestionAnswersDao;

@Service
public class QuestionAnswersService {

	@Autowired
	QuestionAnswersDao questionAnswersDao;

	public void saveQuestionAnswers(QuestionAnswers questionAnswers) {
		if (questionAnswers.getId() != null) {
			
		} else {
			questionAnswers.setCreateTime(new Date());
			questionAnswers.setBrowseCount(0);
			questionAnswers.setCollectCount(0);
			questionAnswersDao.save(questionAnswers);
		}
	}

}

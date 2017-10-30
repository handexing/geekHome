package com.geekhome.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geekhome.common.vo.ErrorCode;
import com.geekhome.common.vo.ExecuteResult;
import com.geekhome.entity.OpenSourceLabel;
import com.geekhome.entity.dao.OpenSourceLabelDao;


@RestController
@RequestMapping("openSource")
public class OpenSourceController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	OpenSourceLabelDao openSourceLabelDao;
	
	@RequestMapping(value = "/getOpenSourcelabelList")
	@CrossOrigin
	public ExecuteResult<List<OpenSourceLabel>> getOpenSourcelabelList(Integer size) {
		final ExecuteResult<List<OpenSourceLabel>> result = new ExecuteResult<>();
		try {
			List<OpenSourceLabel> openSourceLabels = openSourceLabelDao.getOpenSourcelabelTopTen(OpenSourceLabel.OPEN_SOURCE_LABEL_STATE_DEFAULT, size);
			result.setData(openSourceLabels);
			result.setSuccess(true);
		} catch (final Exception e) {
			logger.error("", e);
			result.setSuccess(false);
			result.setErrorCode(ErrorCode.EXCEPTION.getErrorCode());
			result.setErrorMsg(ErrorCode.EXCEPTION.getErrorMsg());
		}
		return result;
	}
}

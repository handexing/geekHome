package com.geekhome.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geekhome.common.vo.ErrorCode;
import com.geekhome.common.vo.ExecuteResult;
import com.geekhome.common.vo.PageableResultJson;
import com.geekhome.entity.Label;
import com.geekhome.entity.OpenSourceContent;
import com.geekhome.entity.dao.LabelDao;
import com.geekhome.entity.dao.OpenSourceContentDao;
import com.geekhome.entity.service.OpenSourceService;

@RestController
@RequestMapping("openSource")
public class OpenSourceController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	LabelDao labelDao;
	@Autowired
	OpenSourceService openSourceService;
	@Autowired
	OpenSourceContentDao openSourceContentDao;

	@RequestMapping(value = "/getOpenSourcelabelList")
	@CrossOrigin
	public ExecuteResult<List<Label>> getOpenSourcelabelList(@RequestParam(value = "types[]") List<Integer> types) {
		final ExecuteResult<List<Label>> result = new ExecuteResult<>();
		try {
			List<Label> labels = labelDao.findLabelByStatusAndType(Label.LABEL_STATE_DEFAULT, types);
			result.setData(labels);
			result.setSuccess(true);
		} catch (final Exception e) {
			logger.error("", e);
			result.setSuccess(false);
			result.setErrorCode(ErrorCode.EXCEPTION.getErrorCode());
			result.setErrorMsg(ErrorCode.EXCEPTION.getErrorMsg());
		}
		return result;
	}

	@RequestMapping("saveOpenSource")
	@CrossOrigin
	public ExecuteResult<Boolean> saveOpenSource(@RequestBody OpenSourceContent openSource) {
		final ExecuteResult<Boolean> result = new ExecuteResult<>();
		try {
			openSourceService.saveOpenSource(openSource);
			result.setSuccess(true);
		} catch (final Exception e) {
			logger.error("", e);
			result.setSuccess(false);
			result.setErrorCode(ErrorCode.EXCEPTION.getErrorCode());
			result.setErrorMsg(ErrorCode.EXCEPTION.getErrorMsg());
		}
		return result;
	}
	
	@RequestMapping("openSourceList")
	@CrossOrigin
	public PageableResultJson openSourceList(Long labelId, Integer page, Integer rows) {
		PageableResultJson tableJson = new PageableResultJson();
		Page<OpenSourceContent> pageData = openSourceService.getOpenSourceListByLabelId(labelId, page, rows);
		tableJson.setData(pageData.getContent());
		tableJson.setPageSize(10);
		tableJson.setTotalPageNumber(pageData.getTotalPages());
		return tableJson;
	}
	
	@RequestMapping("getOpenSourceDetailById")
	@CrossOrigin
	public ExecuteResult<OpenSourceContent> getOpenSourceDetailById(Long id) {
		final ExecuteResult<OpenSourceContent> result = new ExecuteResult<>();
		try {
			OpenSourceContent sourceContent = openSourceService.getOpenSourceDetailById(id);
			result.setData(sourceContent);
			result.setSuccess(true);
		} catch (final Exception e) {
			logger.error("", e);
			result.setSuccess(false);
			result.setErrorCode(ErrorCode.EXCEPTION.getErrorCode());
			result.setErrorMsg(ErrorCode.EXCEPTION.getErrorMsg());
		}
		return result;
	}
	
	@RequestMapping("addBrowseCnt")
	@CrossOrigin
	public ExecuteResult<Boolean> addBrowseCnt(Long id) {
		final ExecuteResult<Boolean> result = new ExecuteResult<>();
		try {
			OpenSourceContent content = openSourceContentDao.findOne(id);
			content.setBrowseCount(content.getBrowseCount() + 1);
			openSourceContentDao.save(content);
			result.setSuccess(true);
		} catch (final Exception e) {
			logger.error("", e);
			result.setSuccess(false);
			result.setErrorCode(ErrorCode.EXCEPTION.getErrorCode());
			result.setErrorMsg(ErrorCode.EXCEPTION.getErrorMsg());
		}
		return result;
	}
	
	@RequestMapping(value = "/getRecommendList")
	@CrossOrigin
	public ExecuteResult<List<OpenSourceContent>> getRecommendList(Long labelId) {
		final ExecuteResult<List<OpenSourceContent>> result = new ExecuteResult<>();
		try {
			List<OpenSourceContent> datas = openSourceContentDao.findOpenSourceContentByStatusAndLabelId(OpenSourceContent.OPEN_SOURCE_CONTENT_STATE_RECOMMEND, labelId);
			result.setData(datas);
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

package com.geekhome.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.geekhome.common.vo.ErrorCode;
import com.geekhome.common.vo.ExecuteResult;
import com.geekhome.entity.Label;
import com.geekhome.entity.dao.LabelDao;
import com.geekhome.entity.service.LabelService;

@RestController
@RequestMapping("label")
public class LabelController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	LabelService labelService;
	@Autowired
	private LabelDao labelDao;

	@RequiresPermissions("label:index")
	@RequestMapping(value = "/labelPage")
	public ModelAndView labelPage() {
		return new ModelAndView("/view/label/labelPage");
	}
	
	@RequiresPermissions("label:allMenu")
	@RequestMapping(value = "/labelList")
	public ExecuteResult<List<Label>> labelList() {
		final ExecuteResult<List<Label>> result = new ExecuteResult<>();
		try {
			ArrayList<Label> labelLists = new ArrayList<>();
			List<Label> labels = labelService.getChildLabelList(labelLists, 0L);
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
	
	@RequiresPermissions("label:save")
	@RequestMapping("saveLabel")
	public ExecuteResult<Boolean> saveLabel(@RequestBody Label label) {
		final ExecuteResult<Boolean> result = new ExecuteResult<>();
		try {
			labelService.saveLabel(label);
			result.setSuccess(true);
		} catch (final Exception e) {
			logger.error("", e);
			result.setSuccess(false);
			result.setErrorCode(ErrorCode.EXCEPTION.getErrorCode());
			result.setErrorMsg(ErrorCode.EXCEPTION.getErrorMsg());
		}
		return result;
	}
	
	@RequiresPermissions("menu:getLabel")
	@RequestMapping("getLabel")
	public ExecuteResult<Label> getLabel(Long id) {
		final ExecuteResult<Label> result = new ExecuteResult<>();
		try {
			Label label = labelDao.findOne(id);
			result.setData(label);
			result.setSuccess(true);
		} catch (final Exception e) {
			logger.error("", e);
			result.setSuccess(false);
			result.setErrorCode(ErrorCode.EXCEPTION.getErrorCode());
			result.setErrorMsg(ErrorCode.EXCEPTION.getErrorMsg());
		}
		return result;
	}
	
	@RequiresPermissions("label:sort")
	@RequestMapping(value = "/updateOrder", method = { RequestMethod.POST })
	public ExecuteResult<Boolean> updateOrder(Long id, Integer sort) {
		final ExecuteResult<Boolean> result = new ExecuteResult<>();
		try {
			labelDao.updateOrder(id,sort);
			result.setSuccess(true);
		} catch (final Exception e) {
			logger.error("", e);
			result.setSuccess(false);
			result.setErrorCode(ErrorCode.EXCEPTION.getErrorCode());
			result.setErrorMsg(ErrorCode.EXCEPTION.getErrorMsg());
		}
		return result;
	}
	
	
	@RequiresPermissions("label:delete")
	@RequestMapping("delLabel")
	public ExecuteResult<Boolean> delLabel(Long id) {
		final ExecuteResult<Boolean> result = new ExecuteResult<>();
		try {
			labelDao.delete(id);
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

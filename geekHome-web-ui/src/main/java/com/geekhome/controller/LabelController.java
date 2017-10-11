package com.geekhome.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	LabelDao labelDao;

	@RequestMapping(value = "/labelList")
	@CrossOrigin
	public ExecuteResult<List<Label>> labelList(Integer type) {
		final ExecuteResult<List<Label>> result = new ExecuteResult<>();
		try {
			List<Label> labels = labelDao.findLabelByStatusAndType(Label.LABEL_STATE_DEFAULT,type);
			List<Label> childs = null;
			for (int i = 0; i < labels.size(); i++) {
				Long id = labels.get(i).getId();
				if(labels.get(i).getParentId()==0) {
					childs = new ArrayList<>();
					for (int j = 0; j < labels.size(); j++) {
						Long parentId = labels.get(j).getParentId();
						if(id == parentId) {
							childs.add(labels.get(j));
						}
					}
					labels.get(i).setChilds(childs);
				}
			}
			
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

}

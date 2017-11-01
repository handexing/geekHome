package com.geekhome.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geekhome.common.vo.ErrorCode;
import com.geekhome.common.vo.ExecuteResult;
import com.geekhome.common.vo.TreeView;
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
	public ExecuteResult<List<Label>> labelList(@RequestParam(value = "types[]") List<Integer> types) {
		final ExecuteResult<List<Label>> result = new ExecuteResult<>();
		try {
			List<Label> labels = labelDao.findLabelByStatusAndType(Label.LABEL_STATE_DEFAULT, types);
			assembleLabel(labels);
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
	
	@RequestMapping(value = "/getAllLabel")
	@CrossOrigin
	public ExecuteResult<List<Label>> getAllLabel(@RequestParam(value = "types[]") List<Integer> types) {
		final ExecuteResult<List<Label>> result = new ExecuteResult<>();
		try {
			List<Label> labels = labelDao.findLabelByStatusAndType(Label.LABEL_STATE_DEFAULT,types);
			assembleLabel(labels);
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

	private void assembleLabel(List<Label> labels) {
		List<Label> childs = null;
		for (int i = 0; i < labels.size(); i++) {
			Long id = labels.get(i).getId();
			if (labels.get(i).getParentId() == 0) {
				childs = new ArrayList<>();
				for (int j = 0; j < labels.size(); j++) {
					Long parentId = labels.get(j).getParentId();
					if (id == parentId) {
						childs.add(labels.get(j));
					}
				}
				labels.get(i).setChilds(childs);
			}
		}
	}

	@RequestMapping(value = "/labelTree")
	@CrossOrigin
	public ExecuteResult<List<TreeView>> labelTree(@RequestParam(value = "types[]") List<Integer> types) {
		final ExecuteResult<List<TreeView>> result = new ExecuteResult<>();
		try {
			List<Label> labels = labelDao.findLabelByStatusAndType(Label.LABEL_STATE_DEFAULT, types);
			List<TreeView> treeList = new ArrayList<>();
			List<TreeView> nodes = null;

			for (int i = 0; i < labels.size(); i++) {
				
				TreeView tree = new TreeView();
				Long id = labels.get(i).getId();
				String lableName = labels.get(i).getLableName();
				
				if (labels.get(i).getParentId() == 0) {
					nodes = new ArrayList<>();
					tree.setText(lableName);
					tree.setLabelId(id);
					tree.setSelectedIcon("");
					tree.setIcon("");
					
					for (int j = 0; j < labels.size(); j++) {
						
						TreeView childsTree = new TreeView();
						Long childId = labels.get(j).getId();
						Long parentId = labels.get(j).getParentId();
						String name = labels.get(j).getLableName();
						
						if (id == parentId) {
							childsTree.setText(name);
							childsTree.setLabelId(childId);
							childsTree.setSelectedIcon("");
							childsTree.setIcon("");
							nodes.add(childsTree);
						}
					}
					tree.setNodes(nodes);
					treeList.add(tree);
				}
			}

			result.setData(treeList);
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

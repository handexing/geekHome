package com.geekhome.entity.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekhome.entity.Label;
import com.geekhome.entity.dao.LabelDao;

@Service
public class LabelService {

	@Autowired
	private LabelDao labelDao;

	public List<Label> getChildLabelList(ArrayList<Label> labelLists, Long parentId) {

		List<Label> List = labelDao.findLabelByParentId(parentId);
		for (Label label : List) {
			labelLists.add(label);
			getChildLabelList(labelLists, label.getId());
		}
		return labelLists;
	}
	
	@Transactional
	public void saveLabel(Label label) {
		if (label.getId() != null) {
			Label m = labelDao.findOne(label.getId());
			m.setUpdateTime(new Date());
			m.setLableName(label.getLableName());
			m.setType(label.getType());
			labelDao.save(m);
		} else {
			label.setCreateTime(new Date());
			label.setSort(0);
			labelDao.save(label);
		}

		if (label.getParentId() != 0) {
			label = labelDao.findOne(label.getParentId());
			label.setUpdateTime(new Date());
			labelDao.save(label);
		}

	}
}

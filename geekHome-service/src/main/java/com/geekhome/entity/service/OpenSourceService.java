package com.geekhome.entity.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekhome.entity.OpenSourceContent;
import com.geekhome.entity.dao.OpenSourceContentDao;

@Service
public class OpenSourceService {

	@Autowired
	OpenSourceContentDao openSourceContentDao;

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

	

}

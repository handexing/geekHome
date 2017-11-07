package com.geekhome.entity.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekhome.entity.BlogType;
import com.geekhome.entity.dao.BlogTypeDao;

@Service
public class BlogTypeService {

	@Autowired
	BlogTypeDao blogTypeDao;

	public Integer save(BlogType blogType) {
		if (blogType.getId() != null) {
			//TODO 修改
		} else {
			List<BlogType> list = blogTypeDao.findBlogTypeByUserIdAndName(blogType.getUserId(),blogType.getName());
			if (list.isEmpty()) {
				blogType.setCreateTime(new Date());
				blogTypeDao.save(blogType);
				return 1;
			} else {
				return -1;// 相同类型相同名称已存在
			}
		}
		return 0;
	}

	
}

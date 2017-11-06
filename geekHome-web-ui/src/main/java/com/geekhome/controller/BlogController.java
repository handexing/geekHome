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
import com.geekhome.entity.Blog;
import com.geekhome.entity.Label;
import com.geekhome.entity.dao.LabelDao;
import com.geekhome.entity.service.BlogService;


@RestController
@RequestMapping("blog")
public class BlogController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	LabelDao labelDao;
	@Autowired
	BlogService blogService;
	
	@RequestMapping(value = "getBloglabelList")
	@CrossOrigin
	public ExecuteResult<List<Label>> getBloglabelList(Integer type,Long userId) {
		final ExecuteResult<List<Label>> result = new ExecuteResult<>();
		try {
			List<Label> labels = labelDao.findLabelByStatusAndTypeAndUserId(Label.LABEL_STATE_DEFAULT,type,userId);
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
	
	@RequestMapping("getBlogList")
	@CrossOrigin
	public PageableResultJson getBlogList(@RequestParam(value = "page") Integer page,Integer rows, Long labelId,Long userId) {
		PageableResultJson tableJson = new PageableResultJson();
		Page<Blog> pageData = blogService.findBlogByUserIdAndLabelIdList(userId,labelId, page,rows);
		tableJson.setData(pageData.getContent());
		tableJson.setPageSize(10);
		tableJson.setTotalPageNumber(pageData.getTotalPages());
		return tableJson;
	}
	
	@RequestMapping("saveBlog")
	@CrossOrigin
	public ExecuteResult<Boolean> saveBlog(@RequestBody Blog blog) {
		final ExecuteResult<Boolean> result = new ExecuteResult<>();
		try {
			blogService.saveBlog(blog);
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

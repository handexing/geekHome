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
import com.geekhome.entity.BlogType;
import com.geekhome.entity.Label;
import com.geekhome.entity.dao.BlogDao;
import com.geekhome.entity.dao.BlogTypeDao;
import com.geekhome.entity.service.BlogService;
import com.geekhome.entity.service.BlogTypeService;


@RestController
@RequestMapping("blog")
public class BlogController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BlogService blogService;
	@Autowired
	BlogDao blogDao;
	@Autowired
	BlogTypeDao blogTypeDao;
	@Autowired
	BlogTypeService blogTypeService;
	
	@RequestMapping(value = "getBloglabelList")
	@CrossOrigin
	public ExecuteResult<List<BlogType>> getBloglabelList(Long userId) {
		final ExecuteResult<List<BlogType>> result = new ExecuteResult<>();
		try {
			List<BlogType> types = blogTypeDao.findUserBlogTypeByStatusAndUserId(Label.LABEL_STATE_DEFAULT,userId);
			result.setData(types);
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
	
	@RequestMapping("saveLabel")
	@CrossOrigin
	public ExecuteResult<Integer> saveLabel(@RequestBody BlogType blogType) {
		final ExecuteResult<Integer> result = new ExecuteResult<>();
		try {
			Integer flag = blogTypeService.save(blogType);
			result.setData(flag);
			result.setSuccess(true);
		} catch (final Exception e) {
			logger.error("", e);
			result.setSuccess(false);
			result.setErrorCode(ErrorCode.EXCEPTION.getErrorCode());
			result.setErrorMsg(ErrorCode.EXCEPTION.getErrorMsg());
		}
		return result;
	}
	
	@RequestMapping("getBlogById")
	@CrossOrigin
	public ExecuteResult<Blog> getBlogById(Long id) {
		final ExecuteResult<Blog> result = new ExecuteResult<>();
		try {
			Blog blog = blogService.getBlogById(id);
			result.setData(blog);
			result.setSuccess(true);
		} catch (final Exception e) {
			logger.error("", e);
			result.setSuccess(false);
			result.setErrorCode(ErrorCode.EXCEPTION.getErrorCode());
			result.setErrorMsg(ErrorCode.EXCEPTION.getErrorMsg());
		}
		return result;
	}
	
	@RequestMapping("addBlogBrowseCnt")
	@CrossOrigin
	public ExecuteResult<Boolean> addBlogBrowseCnt(Long id) {
		final ExecuteResult<Boolean> result = new ExecuteResult<>();
		try {
			Blog blog = blogDao.findOne(id);
			blog.setBrowseCount(blog.getBrowseCount()+1);
			blogDao.save(blog);
			result.setSuccess(true);
		} catch (final Exception e) {
			logger.error("", e);
			result.setSuccess(false);
			result.setErrorCode(ErrorCode.EXCEPTION.getErrorCode());
			result.setErrorMsg(ErrorCode.EXCEPTION.getErrorMsg());
		}
		return result;
	}
	@RequestMapping("getAllBlogList")
	@CrossOrigin
	public PageableResultJson getAllBlogList(@RequestParam(value = "page") Integer page, Integer rows) {
		PageableResultJson tableJson = new PageableResultJson();
		Page<Blog> pageData = blogService.getBlogList(page, rows);
		tableJson.setData(pageData.getContent());
		tableJson.setPageSize(10);
		tableJson.setTotalPageNumber(pageData.getTotalPages());
		return tableJson;
	}
	
}

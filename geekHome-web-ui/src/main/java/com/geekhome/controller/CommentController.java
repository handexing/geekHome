package com.geekhome.controller;

import java.util.Date;
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
import com.geekhome.entity.Comment;
import com.geekhome.entity.User;
import com.geekhome.entity.dao.CommentDao;
import com.geekhome.entity.dao.UserDao;
import com.geekhome.entity.service.CommentService;

@RestController
@RequestMapping("comment")
public class CommentController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CommentService commentService;
	@Autowired
	CommentDao commentDao;
	@Autowired
	UserDao userDao;

	@RequestMapping("saveComment")
	@CrossOrigin
	public ExecuteResult<Boolean> saveComment(@RequestBody Comment comment) {
		final ExecuteResult<Boolean> result = new ExecuteResult<>();
		try {
			comment.setCreateTime(new Date());
			commentDao.save(comment);
			result.setSuccess(true);
		} catch (final Exception e) {
			logger.error("", e);
			result.setSuccess(false);
			result.setErrorCode(ErrorCode.EXCEPTION.getErrorCode());
			result.setErrorMsg(ErrorCode.EXCEPTION.getErrorMsg());
		}
		return result;
	}

	@RequestMapping("commentList")
	@CrossOrigin
	public PageableResultJson commentList(@RequestParam(value = "page") Integer page, Integer rows, Long id, int type) {
		PageableResultJson tableJson = new PageableResultJson();
		Page<Comment> pageData = commentService.findCommentByLabelIdList(id, type, page, rows);
		tableJson.setData(pageData.getContent());
		tableJson.setPageSize(10);
		tableJson.setTotalPageNumber(pageData.getTotalPages());
		return tableJson;
	}
	
	@RequestMapping("getCommentUserList")
	@CrossOrigin
	public ExecuteResult<List<User>> getCommentUserList(Long themeId,Integer type) {
		final ExecuteResult<List<User>> result = new ExecuteResult<>();
		try {
			List<User> list = userDao.getCommentUserByThemeIdAndType(themeId,type);
			result.setData(list);
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

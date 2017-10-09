package com.geekhome.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geekhome.common.utils.PasswordUtil;
import com.geekhome.common.vo.ErrorCode;
import com.geekhome.common.vo.ExecuteResult;
import com.geekhome.entity.User;
import com.geekhome.entity.dao.UserDao;
import com.geekhome.entity.service.UserService;

/**
 * @Description: 用户
 * @author handx
 * @date 2017年9月12日 下午4:07:46
 * @version V1.0
 */
@RestController
@RequestMapping("user")
public class UserController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;
	@Autowired
	private UserDao userDao;

	@RequestMapping("userRegister")
	@CrossOrigin
	public ExecuteResult<Integer> userRegister(@RequestBody User user) {
		final ExecuteResult<Integer> result = new ExecuteResult<>();
		try {
			Integer flag = userService.saveUser(user);
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

	@RequestMapping("userLogin")
	@CrossOrigin
	public ExecuteResult<User> userLogin(String userName, String password) {
		final ExecuteResult<User> result = new ExecuteResult<>();
		try {
			String pwd = PasswordUtil.createCustomPwd(password, userName + User.SALT);
			User user = userDao.findUserByUserNameAndPassword(userName, pwd);
			result.setData(user);
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

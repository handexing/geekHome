package com.geekhome.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geekhome.common.vo.ErrorCode;
import com.geekhome.common.vo.ExecuteResult;

/**
 * @Description: 首页
 * @author handx
 * @date 2017年9月12日 下午4:12:30
 * @version V1.0
 */
@RestController
@RequestMapping("index")
public class IndexController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@RequestMapping("test")
	public ExecuteResult<String> test() {
		ExecuteResult<String> result = new ExecuteResult<>();
		try {
			logger.info("#%$#%#$%#$%#%#$%$#%");
			result.setData("this is test!!!");
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("", e);
			result.setSuccess(false);
			result.setErrorCode(ErrorCode.EXCEPTION.getErrorCode());
			result.setErrorMsg(ErrorCode.EXCEPTION.getErrorMsg());
		}
		return result;
	}
	

}

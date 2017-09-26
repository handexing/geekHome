package com.geekhome.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.geekhome.common.vo.ErrorCode;
import com.geekhome.common.vo.ExecuteResult;
import com.geekhome.entity.Admin;
import com.geekhome.entity.Menu;
import com.geekhome.entity.dao.MenuDao;
import com.geekhome.shiro.config.AdminShiroUtil;
import com.geekhome.utils.MenuTreeUtil;

/**
 * @Description: 后台首页
 * @author handx  
 * @date 2017年9月13日 下午11:11:48 
 * @version V1.0
 */

@RestController
@RequestMapping("index")
public class IndexController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private MenuDao menuDao;

	@RequestMapping("indexPage")
	public ModelAndView indexPage() {
		return new ModelAndView("index");
	}
	
	@RequestMapping("menuList")
	public ExecuteResult<List<Menu>> menuList() {
		final ExecuteResult<List<Menu>> result = new ExecuteResult<>();
		try {
			Admin admin = AdminShiroUtil.getUserInfo();
			List<Menu> menuLists = null;
	        if(admin.getIsSystem() == 1){
	            menuLists = menuDao.findAll();
	        }else{
	            menuLists = menuDao.findMenuById(admin.getId());
	        }
	        MenuTreeUtil menuTreeUtil = new MenuTreeUtil(menuLists,null);
			result.setData(menuTreeUtil.buildTreeGrid());
			result.setSuccess(true);
		} catch (final Exception e) {
			logger.error("", e);
			result.setSuccess(false);
			result.setErrorCode(ErrorCode.EXCEPTION.getErrorCode());
			result.setErrorMsg(ErrorCode.EXCEPTION.getErrorMsg());
		}
		return result;
	}
	
	@RequestMapping("welcomePage")
	public ModelAndView welcomePage() {
		return new ModelAndView("welcome");
	}

}

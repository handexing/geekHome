package com.geekhome.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.geekhome.entity.Admin;

@RestController
@RequestMapping("admin")
public class AdminController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("loginPage")
	public ModelAndView loginPage() {
		return new ModelAndView("login");
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView loginPost(String username, String password) {

		ModelAndView view = new ModelAndView();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		// 获取当前的Subject
		Subject currentUser = SecurityUtils.getSubject();
		try {
			logger.info("对用户[" + username + "]进行登录验证..验证开始");
			currentUser.login(token);
			logger.info("对用户[" + username + "]进行登录验证..验证通过");
		} catch (UnknownAccountException uae) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
			view.addObject("message", "未知账户");
		} catch (IncorrectCredentialsException ice) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
			view.addObject("message", "密码不正确");
		} catch (LockedAccountException lae) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
			view.addObject("message", "账户已锁定");
		} catch (ExcessiveAttemptsException eae) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
			view.addObject("message", "用户名或密码错误次数过多");
		} catch (AuthenticationException ae) {
			// 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
			ae.printStackTrace();
			view.addObject("message", "用户名或密码不正确");
		}
		// 验证是否登录成功
		if (currentUser.isAuthenticated()) {
			Session session = SecurityUtils.getSubject().getSession();
			Admin admin = (Admin) currentUser.getPrincipal();
			session.setAttribute("admin", admin);
			logger.info("用户[" + username + "]登录认证通过");
			view.setViewName("index");
		} else {
			token.clear();
			view.setViewName("login");
		}
		return view;
	}

	@RequestMapping("logout")
	public ModelAndView logout() {
		SecurityUtils.getSubject().logout();
		return new ModelAndView("login");
	}
}

package com.geekhome.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("user")
public class UserController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequiresPermissions("user:index")
	@RequestMapping("userPage")
	public ModelAndView userPage() {
		return new ModelAndView("/view/user/userPage");
	}
	
}

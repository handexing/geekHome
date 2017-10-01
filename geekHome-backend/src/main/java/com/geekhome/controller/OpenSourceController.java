package com.geekhome.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("openSource")
public class OpenSourceController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequiresPermissions("openSource:index")
	@RequestMapping(value = "/openSourcePage")
	public ModelAndView openSourcePage() {
		return new ModelAndView("/view/openSource/openSourcePage");
	}
	
}

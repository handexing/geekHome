package com.geekhome.shiro.config;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

public class AdminFormAuthenticationFilter extends FormAuthenticationFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		return super.isAccessAllowed(request, response, mappedValue);
	}

	@Override
	public void setLoginUrl(String loginUrl) {
		super.setLoginUrl("/admin/loginPage");
	}

	@Override
	public void setSuccessUrl(String successUrl) {
		super.setSuccessUrl("/index/indexPage");
	}

}

package com.geekhome.config;

import javax.annotation.Resource;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.geekhome.entity.dao.IntegralDao;

public class ApiEncyptInterceptor extends HandlerInterceptorAdapter
{
    @Resource
    private IntegralDao integralDao;
}

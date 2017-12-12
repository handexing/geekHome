package com.geekhome.common.utils;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * 获取ApplicationContext和Object的工具类
 * @author py
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Service
public class SpringContextUtils implements ApplicationContextAware
{

    private static ApplicationContext applicationContext;
    
    @Override
    public void setApplicationContext(ApplicationContext arg0) throws BeansException
    {
        applicationContext = arg0;
    }
    
    /**
     * 获取applicationContext对象
     * @return
     */
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
     
    /**
     * 根据bean的id来查找对象
     * @param id
     * @return
     */
    public static Object getBeanById(String id){
        return applicationContext.getBean(id);
    }
     
    /**
     * 根据bean的class来查找对象
     * @param c
     * @return
     */
    public static Object getBeanByClass(Class c){
        return applicationContext.getBean(c);
    }
     
    /**
     * 根据bean的class来查找所有的对象(包括子类)
     * @param c
     * @return
     */
    public static Map getBeansByClass(Class c){
        return applicationContext.getBeansOfType(c);
    }
    
}

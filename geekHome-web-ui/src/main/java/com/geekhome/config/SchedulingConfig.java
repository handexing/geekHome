package com.geekhome.config;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.geekhome.entity.dao.IntegralDao;

/**
 * 定时任务
 * @author p_y20
 *
 */
@Component
public class SchedulingConfig
{
    private final Logger logger = Logger.getLogger(SchedulingConfig.class);
    
    @Autowired
    private IntegralDao integralDao;
    
    @Scheduled(cron = "0 0/10 * * * ?") // 每10分钟执行一次
    public void getToken() {
        logger.info("重置签到定时任务启动");
        integralDao.updateState();
    }
    
}

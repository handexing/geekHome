package com.geekhome.entity.service;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.geekhome.common.utils.DateUtil;
import com.geekhome.entity.Integral;
import com.geekhome.entity.User;
import com.geekhome.entity.dao.IntegralDao;
import com.geekhome.entity.dao.UserDao;

/**
 * 积分service
 * @author p_y20
 *
 */
@Service
public class IntegralService
{
    @Autowired
    private IntegralDao integralDao;
    @Autowired
    private UserDao userDao;
    
    public Integral signInForIntegral(String userName,Integer currentIntegral,HttpServletRequest request)
    {
        Integral integral = integralDao.findIntegralByUserName(userName);
        User user = userDao.findUserByUserName(userName);
        if(integral == null){
            integral = new Integral();
            //如果没查到此积分用户就是初始插入
            integral.setUserId(user.getId());
            integral.setUserName(userName);
            integral.setFirstSignTime(DateUtil.getWebsiteDatetime());//初次签到日期，国际时间
            integral.setIntegral(1);//初次插入积分为1
            integral.setSignCount(1);//签到次数为1
            integral.setState(1);//已签到
            integralDao.save(integral);
            integral = integralDao.findIntegralByUserName(userName);
            user.setIntegralId(integral.getId());//更新user表的积分id
            userDao.save(user);//更新id到user表
        }else {
            integral.setSignCount(integral.getSignCount() + 1);//签到次数加1
            integral.setIntegral(integral.getIntegral() + 1);//积分加1
            integral.setState(1);//已签到
            integralDao.save(integral);//更新插入个人积分
            userDao.save(user);//更新积分
        }
        return integral;
    }
    
    /**
     * 根据用户名查询出用户积分
     * @param userName 用户名
     * @return
     */
    public Integral findIntegralByUserName(String userName)
    {
        return integralDao.findIntegralByUserName(userName);
    }
}

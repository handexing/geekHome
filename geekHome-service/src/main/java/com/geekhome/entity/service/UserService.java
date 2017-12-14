package com.geekhome.entity.service;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekhome.common.utils.PasswordUtil;
import com.geekhome.entity.User;
import com.geekhome.entity.dao.UserDao;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
    EntityManager entityManager;

	public User saveUser(User user) {

		if (user.getId() != null) {
			List<User> list = userDao.findUserByIdNotAndUserName(user.getId(), user.getUserName());
			if (list.isEmpty()) {
				User m_user = userDao.findOne(user.getId());
				user.setCreateTime(m_user.getCreateTime());
				user.setState(m_user.getState());
				user.setSex(m_user.getSex());
				user.setPassword(m_user.getPassword());
				user.setUpdateTime(new Date());
				user.setIntegralId(user.getIntegralId());
				user.setIntegral(user.getIntegral());
				user.setSignUpState(user.getSignUpState());
				userDao.save(user);
				return user;
			} else {
				return new User();// 已存在相同名称
			}
		} else {
			User m_user = userDao.findUserByUserName(user.getUserName());
			if (m_user == null) {
				String password = PasswordUtil.createCustomPwd(user.getPassword(), user.getUserName() + User.SALT);
				user.setPassword(password);
				user.setCreateTime(new Date());
				Random random = new Random();
				int result = random.nextInt(44);
				user.setHeadImgUrl(result + ".png");
				user.setState(User.USER_STATE_DEFAULT);
				user.setBrief("用户很懒，什么都没说明....");
				user.setSex(User.USER_SEX_DEFAULT);
				userDao.save(user);
				return user;
			} else {
				return new User();// 已存在相同名称
			}
		}
	}
	
	/**
	 * 通过用户名和邮箱验证用户是否存在
	 * @param user 传入的用户名和邮箱
	 * @return
	 */
	public User verifyByNameAndEmail(User user){
	    User us = userDao.findUserByUserNameAndEmail(user.getUserName(), user.getEmail());
	    if(us != null) {
	        return us;
	    }else {
	       return new User(); 
	    }
	}
	
	/**
	 * 修改用户密码
	 * @return
	 */
	public Integer modifyPersonPwd(String userName , String pwd ) {
	    Integer num = userDao.modifyPersonPwd(userName, pwd);
	    return num;
	}
	
	/**
	 * 通过用户名密码登录
	 * @return
	 */
	@SuppressWarnings("unchecked")
    public User findUserByUserNameAndPassword(String userName, String password) {
	    StringBuilder strb = new StringBuilder();
	    strb.append("SELECT U.ID id , U.USER_NAME userName , U.SEX sex , U.BIRTHDAY birthday , U.EMAIL email , U.PHONE phone")
	    .append(", U.PASSWORD password , U.BRIEF brief , U.HEAD_IMG_URL headImgUrl , U.STATE state , U.CREATE_TIME createTime , U.UPDATE_TIME updateTime")
	    .append(", U.COMPANY company , U.ADDRESS address , U.GIT_HUB_URL gitHubUrl , U.WEB_SITE_URL webSiteUrl , U.INTEGRAL_ID integralId , I.INTEGRAL integral")
	    .append(",I.STATE signUpState FROM USER U LEFT JOIN INTEGRAL I ON U.ID = I.USER_ID WHERE U.USER_NAME=:userName AND U.PASSWORD=:password");
	    System.out.println(strb.toString());
	    List<User> users = entityManager.createNativeQuery(strb.toString() , "findUserByUserNameAndPassword").setParameter("userName", userName)
	        .setParameter("password", password).getResultList();
	    return users.get(0);
	}
	
}

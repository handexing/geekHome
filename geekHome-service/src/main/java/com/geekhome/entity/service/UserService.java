package com.geekhome.entity.service;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekhome.common.utils.PasswordUtil;
import com.geekhome.entity.User;
import com.geekhome.entity.dao.UserDao;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public Integer saveUser(User user) {

		if (user.getId() != null) {
			List<User> list = userDao.findUserByIdNotAndUserName(user.getId(), user.getUserName());
			if (list.isEmpty()) {
				User m_user = userDao.findOne(user.getId());
				//TODO 后台管理修改
				user.setCreateTime(m_user.getCreateTime());
				user.setUpdateTime(new Date());
				userDao.save(user);
				return 1;
			} else {
				return -1;// 已存在相同名称
			}
		} else {
			User m_user = userDao.findUserByUserName(user.getUserName());
			if (m_user == null) {
				String password = PasswordUtil.createCustomPwd(user.getPassword(),user.getUserName() + User.SALT);
				user.setPassword(password);
				user.setCreateTime(new Date());
				Random random=new Random();
				int result=random.nextInt(44);
				user.setHeadImgUrl("img/"+result+".png");
				user.setState(User.USER_STATE_DEFAULT);
				user.setBrief("用户很懒，什么都没说明....");
				user.setSex(User.USER_SEX_DEFAULT);
				userDao.save(user);
				return 1;
			} else {
				return -1;// 已存在相同名称
			}
		}
	}
	
	/**
	 * 修改个人信息
	 * @param user 用户传入要修改信息
	 * @return
	 */
	public Integer modifyPersonInfo(User user)
	{
	    Integer num = userDao.updatePersonInfo(user.getId(),user.getEmail(),user.getPhone(),user.getCompany(),user.getWebSiteUrl(),user.getAddress(),user.getGitHubUrl(),user.getBrief());
	    return num;
	}
	
}

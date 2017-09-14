package com.geekhome.entity.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geekhome.entity.Admin;

/**
 * @Description: 后台管理用户
 * @author handx  
 * @date 2017年9月14日 下午5:14:07 
 * @version V1.0
 */

public interface AdminDao extends JpaRepository<Admin, Long>{

	Admin findAdminByUserName(String userName);

}

package com.geekhome.entity.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekhome.entity.Role;
import com.geekhome.entity.dao.RoleDao;

@Service
public class RoleService {

	@Autowired
	RoleDao roleDao;

	@Transactional
	public void saveRole(Role role) {
		if (role.getId() != null) {
			
		} else {
			role.setCreateTime(new Date());
			roleDao.save(role);
		}
	}
	

}

package com.geekhome.entity.service;

import java.util.Date;
import java.util.List;

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
	public Integer saveRole(Role role) {
		if (role.getId() != null) {
			List<Role> list = roleDao.findRoleByIdNotAndName(role.getId(),role.getName());
			if(list.isEmpty()) {
				Role r = roleDao.findOne(role.getId());
				role.setCreateTime(r.getCreateTime());
				role.setUpdateTime(new Date());
				roleDao.save(role);
				return 1;
			}else {
				return -1;//已存在相同名称
			}
		} else {
			List<Role> list = roleDao.findRoleByName(role.getName());
			if(list.isEmpty()) {
				role.setCreateTime(new Date());
				roleDao.save(role);
				return 1;
			}else {
				return -1;//已存在相同名称
			}
		}
	}
	

}

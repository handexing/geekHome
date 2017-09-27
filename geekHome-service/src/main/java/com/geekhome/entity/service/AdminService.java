package com.geekhome.entity.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.geekhome.common.utils.PageUtil;
import com.geekhome.entity.Admin;
import com.geekhome.entity.dao.AdminDao;

@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;
	@Autowired
	EntityManager entityManager;
	
	
	@SuppressWarnings("unchecked")
	public Page<Admin> getAdminList(Integer page, Integer rows) {
		
		final String sql = "SELECT a.ID id ,a.USER_NAME userName,a.`PASSWORD` password,a.STATE state,"
				+ "a.IS_SYSTEM isSystem,a.SALT salt,a.CREATE_TIME createTime,a.UPDATE_TIME updateTime,r.NAME roleName "
				+ "FROM admin a" + 
				" LEFT JOIN admin_role ar ON a.ID=ar.ADMIN_ID" + 
				" LEFT JOIN role r ON ar.ROLE_ID=r.ID";
		final int firstRecord = PageUtil.calcPage(page) * rows;
		final List<Admin> list = entityManager.createNativeQuery(sql, "getAdminList").setFirstResult(firstRecord).setMaxResults(rows).getResultList();
		final int total = adminDao.getAdminListCnt();
		final Page<Admin> pages = new PageImpl<>(list, null, total);
		return pages;
	}

	
}

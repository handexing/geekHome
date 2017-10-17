package com.geekhome.entity.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.geekhome.entity.Admin;

/**
 * @Description: 后台管理用户
 * @author handx  
 * @date 2017年9月14日 下午5:14:07 
 * @version V1.0
 */
public interface AdminDao extends JpaRepository<Admin, Long>{

	Admin findAdminByUserName(String userName);

	@Query(nativeQuery = true, value = "SELECT COUNT(1) FROM ADMIN")
	int getAdminListCnt();

	List<Admin> findAdminByIdNotAndUserName(Long id, String userName);

}

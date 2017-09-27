package com.geekhome.entity.dao;

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

//	@Query(nativeQuery = true, value = "SELECT a.ID id ,a.USER_NAME userName,a.`PASSWORD` password,a.STATE state,a.IS_SYSTEM isSystem,a.SALT salt,a.CREATE_TIME createTime,a.UPDATE_TIME updateTime,r.NAME roleName FROM admin a" + 
//			" LEFT JOIN admin_role ar ON a.ID=ar.ADMIN_ID" + 
//			" LEFT JOIN role r ON ar.ROLE_ID=r.ID LIMIT ?,?")
//	List<Admin> getAdminList(@Param("start") Integer start,@Param("length") Integer length);
//
	@Query(nativeQuery = true, value = "SELECT COUNT(1) FROM ADMIN")
	int getAdminListCnt();

}

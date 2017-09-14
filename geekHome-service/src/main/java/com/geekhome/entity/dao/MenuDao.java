package com.geekhome.entity.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.geekhome.entity.Menu;

/**
 * @Description: 菜单	
 * @author handx  
 * @date 2017年9月14日 下午5:14:07 
 * @version V1.0
 */

public interface MenuDao extends JpaRepository<Menu, Long>{

	@Query(nativeQuery = true, value = "SELECT CODE FROM MENU")
	public Set<String> getAllMenuCode();
	
	@Query(nativeQuery = true, value = "SELECT m.CODE FROM MENU m , ROLE_MENU rm, ADMIN_ROLE ar WHERE ar.ADMIN_ID =:id AND m.ID = rm.MENU_ID AND rm.ROLE_ID = ar.ROLE_ID")
	public Set<String> findMenuCodeByUserId(@Param("id") Long id);
}

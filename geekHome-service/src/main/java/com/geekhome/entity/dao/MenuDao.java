package com.geekhome.entity.dao;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.geekhome.entity.Menu;

public interface MenuDao extends JpaRepository<Menu, Long>{

	@Query(nativeQuery = true, value = "SELECT CODE FROM MENU")
	public Set<String> getAllMenuCode();
	
	@Query(nativeQuery = true, value = "SELECT m.CODE FROM MENU m , ROLE_MENU rm, ADMIN_ROLE ar WHERE ar.ADMIN_ID =:id AND m.ID = rm.MENU_ID AND rm.ROLE_ID = ar.ROLE_ID")
	public Set<String> findMenuCodeByUserId(@Param("id") Long id);

	@Query(nativeQuery = true, value = "SELECT * FROM MENU WHERE PARENT_ID=:parentId ORDER BY SORT ASC,CREATE_TIME DESC")
	public List<Menu> findMenuByParentId(@Param("parentId") Long parentId);

	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE MENU SET SORT=:sort WHERE ID =:id")
	int updateOrder(@Param("id") Long id, @Param("sort") Integer sort);
	
	@Query(nativeQuery = true, value = "SELECT COUNT(1) FROM MENU WHERE ID=:parentId")
	int getMenuByParentIdCnt(@Param("parentId") Long parentId);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(nativeQuery = true, value = "DELETE FROM MENU WHERE ID=:parentId OR PARENT_ID=:parentId")
	public void delete(@Param("parentId") Long parentId);

	@Query(nativeQuery = true, value = "SELECT * FROM menu WHERE ID in(SELECT MENU_ID FROM role_menu WHERE ROLE_ID = (SELECT ROLE_ID FROM admin_role WHERE ADMIN_ID=:id))")
	public List<Menu> findMenuById(@Param("id") Long id);
}

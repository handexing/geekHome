package com.geekhome.entity.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.geekhome.entity.RoleMenu;

public interface RoleMenuDao extends JpaRepository<RoleMenu, Long>{

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(nativeQuery = true, value = "DELETE FROM ROLE_MENU WHERE MENU_ID =:id")
	public void delete(@Param("id") Long id);

	public List<RoleMenu> findRoleMenuByroleId(Long id);
}

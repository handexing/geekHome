package com.geekhome.entity.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.geekhome.entity.AdminRole;

public interface AdminRoleDao extends JpaRepository<AdminRole, Long>{

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(nativeQuery = true, value = "DELETE FROM ADMIN_ROLE WHERE ADMIN_ID =:adminId")
	public void deleteAdmin(@Param("adminId") Long adminId);
	
	public List<AdminRole> findAdminRoleByAdminId(Long id);
}

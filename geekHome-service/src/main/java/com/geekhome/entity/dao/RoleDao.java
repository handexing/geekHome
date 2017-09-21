package com.geekhome.entity.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geekhome.entity.Role;

public interface RoleDao extends JpaRepository<Role, Long> {

	public List<Role> findRoleByName(String name);

	public List<Role> findRoleByIdNotAndName(Long id,String name );

}

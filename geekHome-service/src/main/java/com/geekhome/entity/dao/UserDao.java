package com.geekhome.entity.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geekhome.entity.User;

public interface UserDao extends JpaRepository<User, Long>{

	User findUserByUserName(String userName);

	List<User> findUserByIdNotAndUserName(Long id, String userName);

	User findUserByUserNameAndPassword(String userName, String pwd);
	
}

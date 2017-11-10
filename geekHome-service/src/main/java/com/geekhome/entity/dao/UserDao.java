package com.geekhome.entity.dao;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.geekhome.entity.User;

public interface UserDao extends JpaRepository<User, Long>{

	User findUserByUserName(String userName);

	List<User> findUserByIdNotAndUserName(Long id, String userName);

	User findUserByUserNameAndPassword(String userName, String pwd);
	
	User findUserByUserNameAndEmail(String userName, String email);
	
	@Modifying(clearAutomatically = true)
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE USER SET PASSWORD=:password WHERE USER_NAME =:userName")
	int modifyPersonPwd(@Param("userName") String userName, @Param("password") String password);

	User findUserByEmail(String email);
	
}

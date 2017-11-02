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
	
	/**
	 * 更新个人信息
	 * @return
	 */
	@Modifying(clearAutomatically = true)
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE USER SET EMAIL=:email,PHONE=:phone,COMPANY=:company,WEB_SITE_URL=:webSiteUrl,ADDRESS=:address,GIT_HUB_URL=:gitHubUrl,BRIEF=:brief WHERE ID=:id")
	Integer updatePersonInfo(@Param("id") Long id,@Param("email") String email,@Param("phone") String phone,@Param("company") String company,@Param("webSiteUrl") String webSiteUrl,@Param("address") String address,@Param("gitHubUrl") String gitHubUrl,@Param("brief") String brief);

}

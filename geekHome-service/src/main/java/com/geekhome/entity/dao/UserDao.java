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
	
	@Query(nativeQuery = true, value = "SELECT * FROM USER WHERE ID IN(SELECT USER_ID FROM COMMENT WHERE THEME_ID=:themeId AND TYPE=:type UNION ALL SELECT USER_ID FROM COMMENT_REPLY WHERE THEME_ID=:themeId AND TYPE=:type) GROUP BY ID")
	List<User> getCommentUserByThemeIdAndType(@Param("themeId")Long themeId, @Param("type")Integer type);
	
	@Modifying(clearAutomatically = true)
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE USER SET HEAD_IMG_URL=:url WHERE USER_NAME =:userName")
    int modifyAvatar(@Param("url") String url, @Param("userName") String userName);

}

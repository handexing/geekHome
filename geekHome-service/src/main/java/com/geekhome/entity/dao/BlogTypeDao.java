package com.geekhome.entity.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.geekhome.entity.BlogType;

public interface BlogTypeDao extends JpaRepository<BlogType, Long>{

	@Query(nativeQuery = true, value = "SELECT * FROM BLOG_TYPE WHERE STATUS=:status AND USER_ID=:userId ORDER BY CREATE_TIME ASC")
	public List<BlogType> findUserBlogTypeByStatusAndUserId(@Param("status")Integer status, @Param("userId")Long userId);

	public List<BlogType> findBlogTypeByUserIdAndName(Long userId, String name);

}

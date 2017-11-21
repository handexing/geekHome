package com.geekhome.entity.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.geekhome.entity.Blog;

public interface BlogDao extends JpaRepository<Blog, Long>{

	@Query(nativeQuery = true, value = "SELECT COUNT(1) FROM BLOG WHERE TYPE_ID =:typeId")
	int getBlogByUserIdCnt(@Param("typeId") Long typeId);

	@Query(nativeQuery = true, value = "SELECT COUNT(1) FROM BLOG")
	int getBoleCnt();
	
	@Query(nativeQuery = true, value = "SELECT COUNT(1) FROM BLOG WHERE SYSTEM_TYPE_ID=:systemTypeId")
	int getBoleBySystemTypeIdCnt(@Param("systemTypeId") Long systemTypeId);

}

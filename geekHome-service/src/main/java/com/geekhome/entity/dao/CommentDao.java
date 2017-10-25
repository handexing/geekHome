package com.geekhome.entity.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.geekhome.entity.Comment;

public interface CommentDao extends JpaRepository<Comment, Long>{

	
	@Query(nativeQuery = true, value = "SELECT COUNT(1) FROM COMMENT WHERE THEME_ID=:id")
	int getCommentByThemeIdCnt(@Param("id") Long id);

	
}

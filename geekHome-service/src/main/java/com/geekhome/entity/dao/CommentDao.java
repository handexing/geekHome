package com.geekhome.entity.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geekhome.entity.Comment;

public interface CommentDao extends JpaRepository<Comment, Long>{

}

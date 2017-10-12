package com.geekhome.entity.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geekhome.entity.CommentReply;

public interface CommentReplyDao extends JpaRepository<CommentReply, Long>{

}

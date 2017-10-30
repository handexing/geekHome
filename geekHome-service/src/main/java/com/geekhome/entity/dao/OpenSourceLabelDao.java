package com.geekhome.entity.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.geekhome.entity.OpenSourceLabel;

public interface OpenSourceLabelDao extends JpaRepository<OpenSourceLabel, Long>{

	@Query(nativeQuery = true, value = "SELECT * FROM OPEN_SOURCE_LABEL WHERE STATUS=:status ORDER BY SORT ASC LIMIT :size")
	List<OpenSourceLabel> getOpenSourcelabelTopTen(@Param("status")Integer status, @Param("size")Integer size);

}

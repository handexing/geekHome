package com.geekhome.entity.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.geekhome.entity.OpenSourceContent;

public interface OpenSourceContentDao extends JpaRepository<OpenSourceContent, Long>{

	Page<OpenSourceContent> findByLabelId(Long labelId, Pageable pageRequest);

	@Query(nativeQuery = true, value = "SELECT COUNT(1) FROM OPEN_SOURCE_CONTENT WHERE LABEL_ID=:labelId")
	int getOpenSourceBylabelIdCnt(@Param("labelId") Long labelId);

	List<OpenSourceContent> findOpenSourceContentByStatusAndLabelId(Integer openSourceContentStateRecommend,
			Long labelId);

}

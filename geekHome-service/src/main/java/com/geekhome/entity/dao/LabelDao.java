package com.geekhome.entity.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.geekhome.entity.Label;

public interface LabelDao extends JpaRepository<Label, Long>{

	@Query(nativeQuery = true, value = "SELECT * FROM LABEL WHERE PARENT_ID=:parentId ORDER BY SORT ASC,CREATE_TIME DESC")
	public List<Label> findLabelByParentId(@Param("parentId") Long parentId);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE LABEL SET SORT=:sort WHERE ID =:id")
	int updateOrder(@Param("id") Long id, @Param("sort") Integer sort);

	@Query(nativeQuery = true, value = "SELECT * FROM LABEL WHERE STATUS=:status AND TYPE IN(:types) ORDER BY SORT ASC,CREATE_TIME DESC")
	public List<Label> findLabelByStatusAndType(@Param("status")Integer status, @Param("types")List<Integer> types);

	public List<Label> findLabelByStatus(Integer labelStateDefault);

	public List<Label> findLabelByTypeAndLableName(Integer type, String lableName);

	public List<Label> findLabelByIdNotAndTypeAndLableName(Long id, Integer type, String lableName);
	
}

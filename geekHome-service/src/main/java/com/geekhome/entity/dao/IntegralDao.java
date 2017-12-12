package com.geekhome.entity.dao;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.geekhome.entity.Integral;

/**
 * 积分dao
 * @author pengy
 */
public interface IntegralDao extends JpaRepository<Integral, Long>
{
    
    /**
     * 根据用户名查询出用户积分
     * @param userName 用户名
     * @return
     */
    Integral findIntegralByUserName(@Param("userName") String userName);
    
    /**
     *  每天0点定时更新今日已签到为签到
     */
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(nativeQuery = true,value = "UPDATE INTEGRAL SET STATE = 0 WHERE STATE = 1")
    void updateState();
    
}

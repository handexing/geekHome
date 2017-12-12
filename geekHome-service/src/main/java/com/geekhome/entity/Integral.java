package com.geekhome.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 积分表
 * @author p_y20
 *
 */
@Entity
@Table(name = "INTEGRAL")
public class Integral implements Serializable
{

    private static final long serialVersionUID = -5321893512842108390L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;//积分id
    @Column(name = "USER_ID")
    private Long userId;//用户id
    @Column(name = "USER_NAME")
    private String userName;//用户名
    @Column(name = "INTEGRAL")
    private Integer integral;//个人积分
    @Column(name = "FIRST_SIGN_TIME")
    private Date firstSignTime;//第一次登陆时间
    @Column(name = "SIGN_COUNT")
    private Integer signCount;//签到次数
    @Column(name = "STATE")
    private Integer state;//状态 0-今日未签到 1-今日已签到
    
    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }
    public Long getUserId()
    {
        return userId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }
    public Integer getIntegral()
    {
        return integral;
    }
    public void setIntegral(Integer integral)
    {
        this.integral = integral;
    }
    public Date getFirstSignTime()
    {
        return firstSignTime;
    }
    public void setFirstSignTime(Date firstSignTime)
    {
        this.firstSignTime = firstSignTime;
    }
    public Integer getSignCount()
    {
        return signCount;
    }
    public void setSignCount(Integer signCount)
    {
        this.signCount = signCount;
    }
    
    public String getUserName()
    {
        return userName;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    
    public Integer getState()
    {
        return state;
    }
    public void setState(Integer state)
    {
        this.state = state;
    }
    
    @Override
    public String toString()
    {
        return "Integral [id=" + id + ", userId=" + userId + ", userName=" + userName + ", integral=" + integral
            + ", firstSignTime=" + firstSignTime + ", signCount=" + signCount + ", state=" + state + "]";
    }
}

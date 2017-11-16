package com.geekhome.entity;

import java.io.Serializable;

public class VerifyMessage implements Serializable
{

    private static final long serialVersionUID = 2573151426230797296L;
    
    private String emailCode;
    
    private String password;
    
    private String newPassword;
    
    private String userName;
    
    private Integer flag; //用于判断修改的密码信息是忘记密码修改还是直接修改 1-邮箱修改 2-直接修改

    public String getEmailCode()
    {
        return emailCode;
    }

    public void setEmailCode(String emailCode)
    {
        this.emailCode = emailCode;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    
    public Integer getFlag()
    {
        return flag;
    }

    public void setFlag(Integer flag)
    {
        this.flag = flag;
    }
    
    public String getNewPassword()
    {
        return newPassword;
    }

    public void setNewPassword(String newPassword)
    {
        this.newPassword = newPassword;
    }

    @Override
    public String toString()
    {
        return "VerifyMessage [emailCode=" + emailCode + ", password=" + password + ", newPassword=" + newPassword
            + ", userName=" + userName + ", flag=" + flag + "]";
    }
    
}

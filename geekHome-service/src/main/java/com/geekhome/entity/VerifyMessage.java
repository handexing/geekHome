package com.geekhome.entity;

import java.io.Serializable;

public class VerifyMessage implements Serializable
{

    private static final long serialVersionUID = 2573151426230797296L;
    
    private String emailCode;
    
    private String password;
    
    private String userName;

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

    @Override
    public String toString()
    {
        return "VerifyMessage [emailCode=" + emailCode + ", password=" + password + ", userName=" + userName + "]";
    }
    
}

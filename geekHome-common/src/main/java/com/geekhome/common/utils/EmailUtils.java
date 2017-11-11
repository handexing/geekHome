package com.geekhome.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
/**
 * 发送邮件工具类
 * @author p_y20
 *
 */
@Component
public class EmailUtils{
    
    public static String myEmailAccount = "p_y2020@163.com";
    public static String myEmailPassword = "Aa6461166z";
    
    // 网易163邮箱的 SMTP 服务器地址为: smtp.163.com
    public static String myEmailSMTPHost = "smtp.163.com";
    
    @Async("myAsync")  
    public void doTask( String email ,String code ) throws InterruptedException, UnsupportedEncodingException, MessagingException{
      //创建一封邮件
        Properties props = new Properties();                // 用于连接邮件服务器的参数配置（发送邮件时才需要用到）
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
        Session session= Session.getDefaultInstance(props); // 根据参数配置，创建会话对象（为了发送邮件准备的）
        session.setDebug(true); 
        //验证码
        MimeMessage message = createMimeMessage(session,myEmailAccount,email,code);   // 创建邮件对象
        
        //根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();
        
        transport.connect(myEmailAccount, myEmailPassword);
        
        //发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());
        
        transport.close();
    }  

    
    /*public static void sendEmail(String email ,String code) throws MessagingException, IOException
    {
      //创建一封邮件
        Properties props = new Properties();                // 用于连接邮件服务器的参数配置（发送邮件时才需要用到）
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
        Session session= Session.getDefaultInstance(props); // 根据参数配置，创建会话对象（为了发送邮件准备的）
        session.setDebug(true); 
        //验证码
        MimeMessage message = createMimeMessage(session,myEmailAccount,email,code);   // 创建邮件对象
        
        //根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();
        
        transport.connect(myEmailAccount, myEmailPassword);
        
        //发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());
        
        transport.close();
        
    }*/

    private static MimeMessage createMimeMessage(Session session, String sendMail, String receivedEmail,String code) throws UnsupportedEncodingException, MessagingException
    {
        //创建一封邮件
        MimeMessage message = new MimeMessage(session);
        // From: 发件人 
        message.setFrom(new InternetAddress(sendMail, "极客屋官方邮件", "UTF-8"));
        //收件人
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receivedEmail,"极客屋会员用户", "UTF-8"));
        //邮件主题
        message.setSubject("重置密码邮件", "UTF-8");
        //邮件内容
        message.setContent("此次重置验证码为："+ code, "text/html;charset=UTF-8");
        //显示发送时间
        message.setSentDate(new Date());
        //保存
        message.saveChanges();
        // 8. 将该邮件保存到本地
        return message;
    }

    public static String getCode()
    {
        StringBuilder sb = new StringBuilder();
        for( int i = 0; i < 6; i++ )
        {
            sb.append(String.valueOf(new Random().nextInt(10)));
        }
        return sb.toString();
    }

}

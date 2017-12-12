package com.geekhome.config;

import java.io.IOException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;
import com.geekhome.common.utils.SpringContextUtils;
import com.geekhome.entity.Integral;
import com.geekhome.entity.service.IntegralService;

@Component
@ServerEndpoint(value = "/websocket")
public class WebSocket  
{
    @OnOpen  
    public void onOpen(){  
        System.out.println("连接已完成");  
    }
    
    @OnMessage    
    public void say(String message, Session session){  
        try { 
            IntegralService integralService = (IntegralService)SpringContextUtils.getBeanByClass(IntegralService.class);
            Integral integral = integralService.findIntegralByUserName(message);
            session.getBasicRemote().sendText(String.valueOf(integral.getState()));  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    } 
    
    @OnError  
    public void onError(Throwable t){//参数必须加上，不然不能启动  
        System.out.println("error");  
    }  
      
    @OnClose  
    public void onClose(){  
        System.out.println("close");  
    }  
    
}

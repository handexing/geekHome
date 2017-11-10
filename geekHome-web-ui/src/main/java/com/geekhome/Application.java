package com.geekhome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Description: 程序入口
 * @author handx  
 * @date 2017年9月11日 下午9:33:25 
 * @version V1.0
 */

@SpringBootApplication
@EnableScheduling 
@EnableAsync 
public class Application{

	public static void main(String[] args)  {
		SpringApplication.run(Application.class, args);
	}
}

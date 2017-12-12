/*package com.geekhome.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

*//**
 * session 共享
 * @author pengy
 *
 *//*
@Configuration  
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400*30)
public class RedisSessionConfig
{
    @Bean
    public JedisConnectionFactory connectionFactory() {
        return new JedisConnectionFactory();
    }
}
*/
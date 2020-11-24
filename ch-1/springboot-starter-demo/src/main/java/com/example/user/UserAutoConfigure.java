package com.example.user;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置
 */
@Configuration
//作用是启用 @ConfigurationProperties 注解的类
@EnableConfigurationProperties(UserPorperties.class)
public class UserAutoConfigure {

	@Bean
	//bean name : userClient
	@ConditionalOnProperty(prefix = "spring.user",value = "enabled",havingValue = "true")
	public UserClient userClient(UserPorperties userPorperties) {
		return new UserClient(userPorperties);
	}
	
}

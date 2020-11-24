package com.example.user;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;

/**
 * 实体属性
 */
@Data
//@ConfigurationProperties 注解将属性值注入到实体类中
@ConfigurationProperties("spring.user")
public class UserPorperties {

	private String name;
	
}

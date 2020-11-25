package com.example.demo;

import com.example.user.EnableUserClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.example.demo.filter.CustomFilter;

//@EnableUserClient注解与springboot-starter-demo下的spring.factories
// 里的org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.example.user.UserAutoConfigure
// 必须要有一个，否则会报错
// spring.factories 文件里指定了需要被装配的类
// spring-configuration-metadata.json 是配置提示文件，非必需
@EnableUserClient
@SpringBootApplication
public class SpringbootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDemoApplication.class, args);
	}
	
	// 注册过滤器
	@Bean
	//Filter的注册有两种方式，一种是直接用 @WebFilter 注解，另一种是通过 FilterRegistrationBean 进行注册
	public FilterRegistrationBean<CustomFilter> registration() {
		CustomFilter filter = new CustomFilter();
		FilterRegistrationBean<CustomFilter> registration = new FilterRegistrationBean<CustomFilter>(filter);
		registration.addUrlPatterns("/*");
		return registration;
	}

}

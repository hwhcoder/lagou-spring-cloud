package com.example.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动EurekaServerApplication、UserApplication
 * http://localhost:8082/get
 *
 * 给RestTemplate加了负载均衡，使用RestTemplate调用其他服务时，就能生效
 */
@SpringBootApplication
public class RibbonApplication {

	public static void main(String[] args) {
		SpringApplication.run(RibbonApplication.class, args);
	}

}

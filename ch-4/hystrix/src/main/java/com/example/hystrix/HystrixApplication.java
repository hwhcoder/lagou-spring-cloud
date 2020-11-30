package com.example.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

import com.example.hystrix.plugin.CustomHystrixEventNotifier;
import com.example.hystrix.plugin.CustomHystrixPropertiesStrategy;
import com.netflix.hystrix.strategy.HystrixPlugins;

/**
 * 启动EurekaServerApplication、UserApplication
 *
 * http://localhost:8085/get
 */
@EnableHystrix
@SpringBootApplication
public class HystrixApplication {

	public static void main(String[] args) {

		// 手动注册插件，非必需
		HystrixPlugins.getInstance().registerEventNotifier(new CustomHystrixEventNotifier());
//		HystrixPlugins.getInstance().registerPropertiesStrategy(new CustomHystrixPropertiesStrategy());


		SpringApplication.run(HystrixApplication.class, args);
	}

}

package com.example.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * http://localhost:8888/hystrix
 *
 * 打开后，在表单里填写：http://localhost:8085/actuator/hystrix.stream
 * 然后点击 Monitor Stream
 */
@EnableHystrixDashboard
@SpringBootApplication
public class DashboardApplication {
	public static void main(String[] args) {
		SpringApplication.run(DashboardApplication.class, args);
	}
}

package com.example.hystrix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.hystrix.dto.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class HystrixController {

	@Autowired
	private RestTemplate restTemplate;



	/**
	 * Hystrix默认为线程池隔离
	 *
	 * 注意用了线程池隔离模式，再用 ThreadLocal 会有坑
	 *
	 * 如果关掉user-service，则返回 {"id":100,"name":"默认"}
	 *
	 * @return
	 */
	// groupKey 是将一组 command 进行分组，groupKey 就是组的名称
	// 如果没有设置 threadPoolKey 的话，那么线程池的名称会用 groupKey
	// threadPoolKey 是线程池的名称，如果多个 command的threadPoolKey 相同，那么会使用同一个线程池。建议大家手动配置一个简短的、友好的 threadPoolKey，同时使用 threadPoolKey 来对 command 进行线程池隔离的划分
	@HystrixCommand(commandKey="getUser", groupKey="user", fallbackMethod = "fallback", threadPoolKey ="tpk1",
			commandProperties = {
					// 命令执行的超时时间，这个只对线程隔离有效，信号量隔离不支持超时
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000") 
			}
	)
	@GetMapping("/get")
	public Object get() {
		System.err.println(Thread.currentThread().getName());
		User user = restTemplate.getForEntity("http://user-service/user/get?id=1", User.class).getBody();
		return user;
	}



	@HystrixCommand(commandKey="getUser2", groupKey="user",  fallbackMethod = "fallback", threadPoolKey ="tpk1",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "20000") 
			}
	)
	@GetMapping("/get2")
	public Object get2() {
		System.err.println(Thread.currentThread().getName());
		User user = restTemplate.getForEntity("http://user-service/user/get?id=1", User.class).getBody();
		return user;
	}



	@HystrixCommand(commandKey="getUser3", groupKey="user3",  fallbackMethod = "fallback", threadPoolKey ="tpk3",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "20000") 
			},
			threadPoolProperties = {
					@HystrixProperty(name = "queueSizeRejectionThreshold", value = "20") 
			}
	)
	@GetMapping("/get3")
	public Object get3() {
		System.err.println(Thread.currentThread().getName());
		User user = restTemplate.getForEntity("http://user-service/user/get?id=1", User.class).getBody();
		return user;
	}

	public Object fallback() {
		return new User(100, "默认");
	}

}
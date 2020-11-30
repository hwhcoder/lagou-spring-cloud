package com.example.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.feign.config.FeignConfiguration;
import com.example.feign.dto.User;

/**
 *
 * 在接口类上使用 Feign 自带的注解来标识 HTTP 的请求参数信息，
 * 当调用接口对应的方法时，Feign 内部会基于面向接口的动态代理方式生成实现类，将请求调用委托到动态代理实现类，
 * 负责动态代理的组件是 InvocationHandlerFactory
 *
 *
 */
@FeignClient(value = "feign-user-service", configuration = FeignConfiguration.class, /**fallback = UserRemoteClientFallback.class**/ fallbackFactory = UserRemoteClientFallbackFactory.class)
// 本例未抽取出单独的两方包
public interface UserRemoteClient {

//	提供方的写法 ：
//	@GetMapping("/user/get")
//	public User getUser(@RequestParam("id") int id)
//	因此两边的写法是一样的

//	如果本接口作为两方包的话，就可以不用重复定义 API 的 URI 等信息
//	但如果作为两方包，则命名最好不要叫client?
	@GetMapping("/user/get")
	public User getUser(@RequestParam("id")int id);
	
}
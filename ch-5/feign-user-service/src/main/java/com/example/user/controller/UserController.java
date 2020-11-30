package com.example.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.dto.User;

@RestController
public class UserController {

	@GetMapping("/user/get")
	// 关于请求头获取 https://www.jianshu.com/p/f7d9a7a8662b
	public User getUser(@RequestHeader(value = "myHeader", required = false)String myHeader, @RequestParam("id") int id) {
		return new User(id, "hello " + myHeader);
	}
	
}

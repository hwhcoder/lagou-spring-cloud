package com.example.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import com.example.api.StudentRemoteService;

/**
 * 本例未设置回退逻辑
 */
@FeignClient(value = "feign-user-service")
// 接口继承接口
public interface StudentRemoteClient extends StudentRemoteService {

	
}
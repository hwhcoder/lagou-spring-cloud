package com.example.api;

import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.api.request.StudentRequest;

/**
 * 本工程作为两方包
 *
 * 本接口头部没有@FeignClient
 */
public interface StudentRemoteService {

	/**
	 *
	 * @param request
	 * @return
	 */
	@GetMapping("/student/name")
	// 用到了 @SpringQueryMap
	public String getStudentName(@SpringQueryMap StudentRequest request);
	
}

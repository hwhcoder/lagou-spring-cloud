package com.example.api;

import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.api.request.StudentRequest;

/**
 * 本工程作为两方包
 * 本接口头部没有@FeignClient
 */
public interface StudentRemoteService {

	/**
	 * 用到了 @SpringQueryMap
	 * @param request
	 * @return
	 */
	@GetMapping("/student/name")
	public String getStudentName(@SpringQueryMap StudentRequest request);
	
}

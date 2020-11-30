package com.example.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * 请求拦截器
 */
public class CustomRequestInterceptor implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate template) {
		System.err.println(template.url());
		template.header("myHeader", "xx.com");
	}

}

package com.example.feign.decoder;

import java.io.IOException;

import org.springframework.stereotype.Component;


import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;

/**
 * 异常解码器
 *
 * 提供者那边可以模拟异常
 * 注意下，如果不是提供者抛出的异常，例如服务提供者根本没有启动而导致的调用异常，则不会走到这边
 */
@Component
public class FeignClientErrorDecoder implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {
		System.out.println(methodKey + "\t" + response.status());
		try {
			String errorContent = Util.toString(response.body().asReader());
			return new Exception(errorContent);
		} catch (IOException e) {
			return new Exception(e.getMessage());
		}
	}

}

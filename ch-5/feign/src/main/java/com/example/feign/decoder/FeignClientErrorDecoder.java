package com.example.feign.decoder;

import java.io.IOException;

import org.springframework.stereotype.Component;


import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;

/**
 * 解码组件
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

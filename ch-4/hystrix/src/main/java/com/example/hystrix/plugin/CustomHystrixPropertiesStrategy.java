package com.example.hystrix.plugin;

import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.strategy.properties.HystrixPropertiesCommandDefault;
import com.netflix.hystrix.strategy.properties.HystrixPropertiesStrategy;

/**
 * 自定义属性配置
 * @author yinjihuan
 *
 */
public class CustomHystrixPropertiesStrategy extends HystrixPropertiesStrategy {

	/**
	 * 每个commandKey第一次访问时，会执行本方法
	 * @param commandKey
	 * @param builder
	 * @return
	 */
	public HystrixCommandProperties getCommandProperties(HystrixCommandKey commandKey, HystrixCommandProperties.Setter builder) {
		System.out.println(commandKey + "****" + builder.toString());
		// 这里可以自己控制
		return new HystrixPropertiesCommandDefault(commandKey, builder);
	}
	
}

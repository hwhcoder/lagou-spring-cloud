package com.example.apollo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * Apollo 中，对于配置的修改，也可以选择灰度发布，推送到某个指定的节点，
 * 如果这个节点使用一段时间没有发生问题，那么就可以推送给全部节点。
 * 如果这个节点使用过程中出问题，那么直接回滚这个节点的配置即可，对于其他节点没有任何影响。
 */
@SpringBootApplication
public class ApolloApplication {

	public static void main(String[] args) {
		// 指定环境（开发演示用，不能用于生产环境））
		System.setProperty("env", "DEV");
		SpringApplication.run(ApolloApplication.class, args);
	}

}

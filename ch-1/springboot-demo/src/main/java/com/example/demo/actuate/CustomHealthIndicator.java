package com.example.demo.actuate;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * 自定义的HealthIndicator
 * /actuator/health
 */
@Component
public class CustomHealthIndicator extends AbstractHealthIndicator {
	
	@Override
	protected void doHealthCheck(Health.Builder builder) throws Exception {
		builder.up().withDetail("customInfo", "自定义信息");
	}

}

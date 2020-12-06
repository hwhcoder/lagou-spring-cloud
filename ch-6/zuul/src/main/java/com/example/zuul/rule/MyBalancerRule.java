package com.example.zuul.rule;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.Server;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import com.netflix.zuul.context.RequestContext;

@Component
public class MyBalancerRule extends RoundRobinRule {

	@Override
	public Server choose(Object key) {
//		获取 Request 对象只能在信号量隔离的模式下使用，线程隔离获取的 Request 对象为空，
//		这是因为 RequestContext 是基于 ThreadLocal 来实现的，ThreadLocal 跨线程后将无法获取

		// 获取request只能在信号量隔离下使用，线程隔离下ThreadLocal无法使用，解决方案参考：http://cxytiandi.com/blog/detail/18782
		HttpServletRequest req = (HttpServletRequest)RequestContext.getCurrentContext().getRequest();
		String version = req.getHeader("version");
		if (StringUtils.isNotBlank(version)) {
			List<Server> servers = super.getLoadBalancer().getReachableServers();
			List<DiscoveryEnabledServer> list = servers.stream().map(s -> (DiscoveryEnabledServer)s).collect(Collectors.toList());
			Optional<DiscoveryEnabledServer> optional = list.stream()
					.filter(s -> version.equals(s.getInstanceInfo().getMetadata().get("version"))).findFirst();
			if (optional.isPresent()) {
				return optional.get();
			}
		}
		return super.choose(key);
	}

	@Override
	public void initWithNiwsConfig(IClientConfig clientConfig) {
		
	}

}

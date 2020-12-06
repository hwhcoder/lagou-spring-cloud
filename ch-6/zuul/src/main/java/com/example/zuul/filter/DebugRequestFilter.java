package com.example.zuul.filter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.util.Pair;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * 自定义过滤器
 *
 * 另外：
 * Zuul 中自带了一个 DebugFilter，会将执行过程中的一些信息记录起来，
 * 方便调试和问题排查，我们可以通过配置 zuul.include-debug-header=true 来开启这个 Debug 模式，
 * 然后在访问请求的时候，在后面追加一个 debug=true 的参数告诉 Zuul 当前请求的调试信息需要通过响应头进行输出，
 * 这样在这个请求的响应头中就有了 Debug 相关的信息。
 */
@Component
public class DebugRequestFilter extends ZuulFilter {
	
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }
    
    @Override
    public int filterOrder() {
        return 1;
    }
    
    @Override
    public boolean shouldFilter() {
    	RequestContext context = RequestContext.getCurrentContext();
    	HttpServletRequest request = context.getRequest();
		if ("true".equals(request.getParameter("debug"))) {
			return true;
		}
		// 获取前面Filter设置的值
		return context.getBoolean("shouldFilter", false);
    }
    
    @Override
    public Object run() {
        HttpServletRequest req = (HttpServletRequest)RequestContext.getCurrentContext().getRequest();
        System.err.println("REQUEST:: " + req.getScheme() + " " + req.getRemoteAddr() + ":" + req.getRemotePort());
        StringBuilder params = new StringBuilder("?");
        // 获取URL参数
        Enumeration<String> names = req.getParameterNames();
        if( req.getMethod().equals("GET") ) {
           while (names.hasMoreElements()) {
                 String name = (String) names.nextElement();
                 params.append(name);
                 params.append("=");
                 params.append(req.getParameter(name));
                 params.append("&");
              }
        }
        if (params.length() > 0) {
            params.delete(params.length()-1, params.length());
        }
        System.err.println("REQUEST:: > " + req.getMethod() + " " + req.getRequestURI() + params + " " + req.getProtocol());
        Enumeration<String> headers = req.getHeaderNames();
        while (headers.hasMoreElements()) {
            String name = (String) headers.nextElement();
            String value = req.getHeader(name);
            System.err.println("REQUEST:: > " + name + ":" + value);
        }
        final RequestContext ctx = RequestContext.getCurrentContext();
        // 获取请求体参数
        if (!ctx.isChunkedRequestBody()) {
            ServletInputStream inp = null;
            try {
                inp = ctx.getRequest().getInputStream();
                String body = null;
                if (inp != null) {
                    body = IOUtils.toString(inp);
                    System.err.println("REQUEST:: > " + body);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        List<Pair<String, String>>  headerList = RequestContext.getCurrentContext().getOriginResponseHeaders();
        for (Pair<String, String> pair : headerList) {
        	 System.err.println("RESPONSE HEADER:: > " +pair.second());
        }
        // 第一种，获取响应结果
       /* try {
        	Object zuulResponse = RequestContext.getCurrentContext().get("zuulResponse");
        	if (zuulResponse != null) {
        		RibbonHttpResponse resp = (RibbonHttpResponse) zuulResponse;
    			String body = IOUtils.toString(resp.getBody());
    			System.err.println("RESPONSE:: > " + body);
    			resp.close();
    			RequestContext.getCurrentContext().setResponseBody(body);
        	}
		} catch (IOException e) {
			e.printStackTrace();
		}*/
        
        // 第二种，获取响应结果
       InputStream stream = RequestContext.getCurrentContext().getResponseDataStream();
		try {
			if (stream != null) {
				String body = IOUtils.toString(stream);
				System.err.println("RESPONSE:: > " + body);
				RequestContext.getCurrentContext().setResponseBody(body);
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
    }
}

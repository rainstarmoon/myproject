package com.myproject.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class MyFilter extends ZuulFilter {

	@Override
	public String filterType() {
		String type=null;
		//路由之前
		type=FilterConstants.PRE_TYPE;
		//路由之时
		//type="routing";
		//路由之后
		//type="post";
		//发送错误调用
		//type="error";
		return type;
	}

	@Override
	public int filterOrder() {
		//过滤的顺序,order值越大,优先级越低
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		//本文true,永远过滤
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		Object accessToken = request.getParameter("token");
		if (accessToken == null) {
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			try {
				ctx.getResponse().getWriter().write("token is empty");
				ctx.set("isError", true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}

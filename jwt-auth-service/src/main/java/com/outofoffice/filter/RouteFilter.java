package com.outofoffice.filter;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.netflix.zuul.ZuulFilter;
@CrossOrigin(origins = "http://localhost:4200")
public class RouteFilter extends ZuulFilter {

	@Override
	public String filterType() {
		return "route";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		System.out.println("Using Route Filter");

		return null;
	}

}

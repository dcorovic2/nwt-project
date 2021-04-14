package com.outofoffice.notficationsservice.interceptor;

import java.sql.Timestamp;
import java.time.Instant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Component
public class LoggerInterceptor implements HandlerInterceptor {
	Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
//	final static Logger log = LoggerFactory.getLogger(LoggerInterceptor.class);
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3) throws Exception {
		log.info("Request is complete");
		
//		log.info("Request URL: " + request.getRequestURL().toString() + ":: End Time =" + System.currentTimeMillis());
//		log.info("Request URL: " + request.getRequestURL().toString() + ":: Time Taken =" + (System.currentTimeMillis() - startTime));
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model) throws Exception {
		log.info("Handler execution is complete");
		
		System.out.println("\nTimestamp: " + new Timestamp(System.currentTimeMillis()) + "\nResponse: " + response.getStatus());
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		System.out.println("we are Intercepting the Request");
		log.info("PreHandle method is calling");
		long startTime = System.currentTimeMillis();	
	
		//TODO - naziv microservisa
		Instant instant = Instant.now();
		
		log.info("\nTimestamp: " + new Timestamp(System.currentTimeMillis()) + "\nMicroservice: "  + "\nMethod: " + request.getMethod());

			System.out.println("BACIO EXCEPTION!!");
		return true;
	}
}
package com.outofoffice.holidayservice.interceptor;

import java.sql.Timestamp;
import java.time.Instant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.outofoffice.grpc.eventsGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Component
public class LoggerInterceptor implements HandlerInterceptor {
	Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
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
		log.info("PreHandle method is calling");
		long startTime = System.currentTimeMillis();
				
	
		//TODO - naziv microservisa
		Instant instant = Instant.now();
		
		log.info("\nTimestamp: " + new Timestamp(System.currentTimeMillis()) + "\nMicroservice: "  + "\nMethod: " + request.getMethod());
		
		return true;
	}
}

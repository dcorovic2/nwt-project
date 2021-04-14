package com.outofoffice.leaverequestservice.interceptor;


import java.sql.Date;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.google.protobuf.Timestamp;

import events.grpc.Events;
import events.grpc.eventsGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Component
public class LoggerInterceptor implements HandlerInterceptor {
	Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
	ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9091).usePlaintext().build();
    eventsGrpc.eventsBlockingStub stub =  eventsGrpc.newBlockingStub(channel);
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3) throws Exception {
		log.info("Request is complete");
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model) throws Exception {
		log.info("Handler execution is complete");
		
		Instant time = Instant.now();
	    Timestamp timestamp = Timestamp.newBuilder().setSeconds(time.getEpochSecond()).setNanos(time.getNano()).build();
	    
	    Events.APIRequest requestGRPC = Events.APIRequest.newBuilder().setAction(request.getMethod()).setResource(request.getRequestURI()).setService("leaverequest-service").setLiveStartDate(timestamp)
	    		.setStatus(response.getStatus()).build();
	    Events.APIResponse responseGRPC = stub.tracking(requestGRPC);
	    
	    log.info("\nRESPONSE:\nStatus : " + responseGRPC.getStatus() + "\nTimestamp: " + new Date(responseGRPC.getLiveStartDate().getSeconds() * 1000) +
	    		"\nMethod: " + responseGRPC.getAction() + "\nMicroservice: " + responseGRPC.getService() + "\nResource: " + responseGRPC.getResource());

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		log.info("PreHandle method is calling");
		
		Instant time = Instant.now();
	    Timestamp timestamp = Timestamp.newBuilder().setSeconds(time.getEpochSecond()).setNanos(time.getNano()).build();
	    
	    Events.APIRequest requestGRPC = Events.APIRequest.newBuilder().setAction(request.getMethod()).setResource(request.getRequestURI()).setService("leaverequest-service").setLiveStartDate(timestamp)
	    		.setStatus(response.getStatus()).build();
	    Events.APIResponse responseGRPC = stub.tracking(requestGRPC);
	    
	    log.info("\nREQUEST:\nStatus : " + responseGRPC.getStatus() + "\nTimestamp: " + new Date(responseGRPC.getLiveStartDate().getSeconds() * 1000) +
	    		"\nMethod: " + responseGRPC.getAction() + "\nMicroservice: " + responseGRPC.getService() + "\nResource: " + responseGRPC.getResource());
	
		return true;
	}
}
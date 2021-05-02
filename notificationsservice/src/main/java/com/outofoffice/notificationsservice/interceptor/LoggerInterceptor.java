package com.outofoffice.notificationsservice.interceptor;
import java.time.Instant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.google.protobuf.Timestamp;

import events.grpc.Events;
import events.grpc.eventsGrpc;

import java.sql.Date;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Component
public class LoggerInterceptor implements HandlerInterceptor {
	Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
	ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9091).usePlaintext().build();
    eventsGrpc.eventsBlockingStub stub =  eventsGrpc.newBlockingStub(channel);
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3) throws Exception {
		try {
			log.info("Handler execution is complete");
			
			Instant time = Instant.now();
		    Timestamp timestamp = Timestamp.newBuilder().setSeconds(time.getEpochSecond()).setNanos(time.getNano()).build();
		    
		    Events.APIRequest requestGRPC = Events.APIRequest.newBuilder().setAction(request.getMethod()).setResource(request.getRequestURI()).setService("notification-service").setLiveStartDate(timestamp)
		    		.setStatus(response.getStatus()).build();
		    Events.APIResponse responseGRPC = stub.tracking(requestGRPC);
		    
		    log.info("\nRESPONSE:\nStatus : " + responseGRPC.getStatus() + "\nTimestamp: " + new Date(responseGRPC.getLiveStartDate().getSeconds() * 1000) +
		    		"\nMethod: " + responseGRPC.getAction() + "\nMicroservice: " + responseGRPC.getService() + "\nResource: " + responseGRPC.getResource());
			} catch (Exception e) {
				System.out.print("Pao iz posthandle");
				log.info("Status: " + HttpStatus.SERVICE_UNAVAILABLE);
			}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		return true;
	}
}

package com.outofoffice.leaverequestservice.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.outofoffice.leaverequestservice.configuration.RabbitConfiguration;
import com.outofoffice.leaverequestservice.errorhandling.NotFoundException;
import com.outofoffice.leaverequestservice.model.LeaveRequest;
import com.outofoffice.leaverequestservice.repository.LeaveRequestRepository;
import com.outofoffice.leaverequestservice.requestobjects.LeaveStatusRequest;
import com.outofoffice.leaverequestservice.requestobjects.RequestEmployee;
import com.outofoffice.leaverequestservice.requestobjects.RequestNotification;
import com.outofoffice.leaverequestservice.service.LeaveRequestService;

@Component
public class EmployeeResponse {
	
	private final LeaveRequestRepository leaveRequestRepository;
	private final LeaveRequestService leaveRequestService;
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	public EmployeeResponse (LeaveRequestRepository leaveRequestRepository, LeaveRequestService leaveRequestService) {
		super();
		this.leaveRequestRepository = leaveRequestRepository;
		this.leaveRequestService = leaveRequestService;
	}
	
	@RabbitListener(queues = RabbitConfiguration.QUEUE3)
	public void consumeMessageFromEmployeeQueue (RequestNotification statusRequest) {
		LeaveRequest request = new LeaveRequest();
		request = leaveRequestRepository.findById(statusRequest.getRequestId())
				.orElseThrow(() -> new NotFoundException("", "Leave Request", "ID", ""));
		
		if(statusRequest.getBackup()) {
			RequestNotification reqNotification = new RequestNotification(request.getEmployeeId(), statusRequest.getNotificationsId(),
					statusRequest.getStatusId(), statusRequest.getReason(), request.getId(), request.getRestDaysNum());
			
			rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE, RabbitConfiguration.ROUTING_KEY, reqNotification);
		} else {
			System.out.println("Employee with id can not be updated; LeaveRequest DB backup!" );
			LeaveStatusRequest status = new LeaveStatusRequest(statusRequest.getStatusId().intValue(), statusRequest.getStatusId(), statusRequest.getReason());	
			leaveRequestService.updateRequestStatus(status, statusRequest.getRequestId());
		}
	}

}

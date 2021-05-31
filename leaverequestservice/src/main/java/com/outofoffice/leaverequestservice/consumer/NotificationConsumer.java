package com.outofoffice.leaverequestservice.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.outofoffice.leaverequestservice.configuration.RabbitConfiguration;
import com.outofoffice.leaverequestservice.errorhandling.NotFoundException;
import com.outofoffice.leaverequestservice.model.LeaveRequest;
import com.outofoffice.leaverequestservice.repository.LeaveRequestRepository;
import com.outofoffice.leaverequestservice.responseobjects.NotificationIdResponse;



@Component
public class NotificationConsumer {
	
	private final LeaveRequestRepository leaveRequestRepository;
	
	public NotificationConsumer(LeaveRequestRepository leaveRequestRepository) {
		this.leaveRequestRepository = leaveRequestRepository;
	}
	
	@RabbitListener(queues = RabbitConfiguration.QUEUE6)
	public void consumeMessageFromRequestNotification (NotificationIdResponse notifId) {
		LeaveRequest request;
		request = leaveRequestRepository.findById(notifId.getRequestId())
				.orElseThrow(() -> new NotFoundException("", "Leave Request", "ID", ""));
		
		request.setNotificationId(notifId.getNotificationId());

		leaveRequestRepository.save(request);
	}
}

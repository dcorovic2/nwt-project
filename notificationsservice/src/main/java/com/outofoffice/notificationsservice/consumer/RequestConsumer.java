package com.outofoffice.notificationsservice.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.outofoffice.notificationsservice.configuration.RabbitConfiguration;
import com.outofoffice.notificationsservice.errorhandling.NotFoundException;
import com.outofoffice.notificationsservice.model.Notification;
import com.outofoffice.notificationsservice.responseobjects.RequestNotification;
import com.outofoffice.notificationsservice.service.NotificationService;

@Component
public class RequestConsumer {
	
	private final NotificationService notificationService;
	
	public RequestConsumer(NotificationService notificationService) {
		super();
		this.notificationService = notificationService;
	}
	
	@RabbitListener(queues = RabbitConfiguration.QUEUE)
	public void consumeMessageFromRequestQueue (RequestNotification requestNotification) {
		System.out.println("Message recieved from LeaveRequest MS !");
		Notification notification = notificationService.insertNotificationForRequest(requestNotification.getRequestId(), requestNotification.getEmployeeId(),
				requestNotification.getNotificationsId(), requestNotification.getReason());
		System.out.println("Notification(for EMPLOYEE) created!");
	}
	

}

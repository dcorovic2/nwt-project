package com.outofoffice.notificationsservice.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.outofoffice.notificationsservice.configuration.RabbitConfiguration;
import com.outofoffice.notificationsservice.model.Notification;
import com.outofoffice.notificationsservice.requestobjects.AdminNotification;
import com.outofoffice.notificationsservice.responseobjects.RequestNotification;
import com.outofoffice.notificationsservice.service.NotificationService;

@Component
public class AdminNotificationConsumer {
private final NotificationService notificationService;
	
	public AdminNotificationConsumer(NotificationService notificationService) {
		super();
		this.notificationService = notificationService;
	}
	
	@RabbitListener(queues = RabbitConfiguration.QUEUE5)
	public void consumeMessageFromRequestQueue (AdminNotification adminNotification) {
		System.out.println("Message recieved from LeaveRequest MS !");
		Notification notification = notificationService.insertNotificationForAdmin(adminNotification.getEmployeeId(),adminNotification.getComment(), adminNotification.getRequestId(),adminNotification.getDisplayName());
		System.out.println("Notification(for ADMIN) created!");
	}
}
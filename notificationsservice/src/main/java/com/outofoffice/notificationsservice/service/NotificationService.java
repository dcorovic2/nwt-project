package com.outofoffice.notificationsservice.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outofoffice.notificationsservice.model.Notification;
import com.outofoffice.notificationsservice.repository.NotificationsRepository;

@Service
public class NotificationService {

	private final NotificationsRepository notificationRepository;

	public NotificationService(NotificationsRepository NotificationsRepository) {
		this.notificationRepository =  NotificationsRepository;
	}

	public List<Notification> insertBulkNotifications(List<Notification> notifications) {
		List<Notification> insertedNotifications = new ArrayList<Notification>();
		notifications.forEach(notification ->  {
			insertedNotifications.add(insertNotification(notification));
		});
		
		return insertedNotifications;
	}
	
	public Notification insertNotification(Notification notification) {
		return notificationRepository.save(notification);
	}

	public Notification updateNotification(Notification notification) {
		return notificationRepository.save(notification);
	}
	
	public Long deleteNotification(Long id) throws Exception {
		final Optional<Notification> notificationForDelete =  notificationRepository.findById(id);
		if (!notificationForDelete.isPresent()) {
			throw new Exception("Notification with given id does not exist");
		}
		notificationRepository.delete(notificationForDelete.get());
		return notificationForDelete.get().getId();
	}
}

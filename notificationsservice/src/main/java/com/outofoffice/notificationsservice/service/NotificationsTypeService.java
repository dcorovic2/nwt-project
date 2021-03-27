package com.outofoffice.notificationsservice.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.outofoffice.notificationsservice.errorhandling.NoDataException;
import com.outofoffice.notificationsservice.errorhandling.NotFoundException;
import com.outofoffice.notificationsservice.model.NotificationsType;
import com.outofoffice.notificationsservice.repository.NotificationsTypeRepository;
import com.outofoffice.notificationsservice.requestobjects.NotificationTypeRequest;

@Service
public class NotificationsTypeService {

	private final NotificationsTypeRepository notificationsTypeRepository;

	public NotificationsTypeService(NotificationsTypeRepository NotificationsTypeRepository) {
		this.notificationsTypeRepository = NotificationsTypeRepository;
	}

	public ResponseEntity<?> insertNotificationsType(NotificationTypeRequest notif) {
		NotificationsType notification = new NotificationsType(notif.getCode(), notif.getDisplayName(),
				notif.getName());
		NotificationsType newnotif = notificationsTypeRepository.save(notification);
		return new ResponseEntity<>(newnotif, HttpStatus.OK);

	}

	public NotificationsType getById(long id) {
		String id_string = id + "";
		return notificationsTypeRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(id_string, "NotificationType", "ID", ""));
	}

	public ResponseEntity<?> getNotificationTypeList() {
		List<NotificationsType> notificationList = notificationsTypeRepository.findAll();
		if (notificationList.isEmpty())
			throw new NoDataException();
		else
			return new ResponseEntity<>(notificationList, HttpStatus.OK);
	}

	public ResponseEntity<?> updateNotification_type(NotificationTypeRequest notification_type, Long id) {
		String id_string = id + " ";
		NotificationsType updateNotification = notificationsTypeRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(id_string, "Notification", "ID", ""));
		updateNotification.setCode(notification_type.getCode());
		updateNotification.setDisplayName(notification_type.getDisplayName());
		updateNotification.setName(notification_type.getName());
		NotificationsType upnotif = notificationsTypeRepository.save(updateNotification);
		return new ResponseEntity<>(upnotif, HttpStatus.OK);
	}

	public ResponseEntity<?> deleteNotificationType(Long id) {
		String id_string = id + " ";
		NotificationsType notificationTypeForDelete = notificationsTypeRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(id_string, "NotificationType", "ID", ""));
		notificationsTypeRepository.delete(notificationTypeForDelete);
		Long deleteNotif = notificationTypeForDelete.getId();
		return new ResponseEntity<Long>(deleteNotif, HttpStatus.OK);

	}

}

package com.outofoffice.notificationsservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.outofoffice.notificationsservice.error.ErrorMessage;
import com.outofoffice.notificationsservice.model.Notification;
import com.outofoffice.notificationsservice.model.NotificationsType;
import com.outofoffice.notificationsservice.repository.NotificationsTypeRepository;
import com.outofoffice.notificationsservice.requestobjects.NotificationRequest;
import com.outofoffice.notificationsservice.requestobjects.NotificationTypeRequest;


@Service
public class NotificationsTypeService {

	private final NotificationsTypeRepository notificationsTypeRepository;
	private final ErrorMessage errorMessage =  new ErrorMessage("Something went wrong!");

	public NotificationsTypeService(NotificationsTypeRepository NotificationsTypeRepository) {
		this.notificationsTypeRepository =  NotificationsTypeRepository;
	}

	
	public ResponseEntity<?> insertNotificationsType(NotificationTypeRequest notif) {
		try {
		NotificationsType notification = new NotificationsType(notif.getCode(),notif.getDisplayName(),notif.getName());	
		NotificationsType newnotif = notificationsTypeRepository.save(notification);
		return new ResponseEntity<>(newnotif, HttpStatus.OK);
		} catch (Exception e) {
		  	  return null;
	 }
		}

	
	public NotificationsType getById(long id) {
		try {
		return notificationsTypeRepository.findById(id).get();
		} catch (Exception e) {
		  	  return null;
	 }
	}
	

	
	public ResponseEntity<?> getNotificationTypeList() {
	    try {
	         List<NotificationsType> notificationList = notificationsTypeRepository.findAll();
	         return new ResponseEntity<>(notificationList, HttpStatus.OK);
	    } catch (Exception e) {
	         return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	     }
	  }
	
	public ResponseEntity<?> updateNotification_type(NotificationTypeRequest notification_type, Long id){
	    try {
	    	NotificationsType updateNotification = notificationsTypeRepository.findById(id).get();
	    	updateNotification.setCode(notification_type.getCode());
	    	updateNotification.setDisplayName(notification_type.getDisplayName());
	    	updateNotification.setName(notification_type.getName());
	    	NotificationsType upnotif = notificationsTypeRepository.save(updateNotification);
	    	return new ResponseEntity<>(upnotif, HttpStatus.OK);
	    } catch (Exception e) {
	    	  return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	public ResponseEntity<?> deleteNotificationType(Long id) {
		try {
		final Optional<NotificationsType> notificationTypeForDelete =  notificationsTypeRepository.findById(id);
		if (notificationTypeForDelete.isEmpty()) {
			return new ResponseEntity<>(new ErrorMessage("Notification type with given id does not exit!"),HttpStatus.NOT_FOUND);
		}
		notificationsTypeRepository.delete(notificationTypeForDelete.get());
		Long deleteNotif = notificationTypeForDelete.get().getId();	
		return new ResponseEntity<Long>(deleteNotif, HttpStatus.OK);
		
		} catch (Exception e) {
	    	  return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
      }
	}
	
}








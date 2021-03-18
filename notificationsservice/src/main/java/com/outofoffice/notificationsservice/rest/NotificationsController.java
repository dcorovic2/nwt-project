package com.outofoffice.notificationsservice.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.outofoffice.notificationsservice.service.NotificationService;
import com.outofoffice.notificationsservice.model.Notification;


import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/")
public class NotificationsController {
	
	private final NotificationService notificationService;

	public NotificationsController(NotificationService NotificationService) {
		this.notificationService = NotificationService;
	}

	@PostMapping(value = "/bulkNotificationCreate")
	public ResponseEntity<List<Notification>> bulkInsert(@RequestBody List<Notification> requestNotifications) {
		List<Notification> notifications = notificationService.insertBulkNotifications(requestNotifications);
		return new ResponseEntity<List<Notification>>(notifications, HttpStatus.CREATED);

	}

	@PostMapping(value = "/notifications")
	public ResponseEntity<Notification> insertNotification(@RequestBody Notification requestNotification) {
		Notification notification = notificationService.insertNotification(requestNotification);
		return new ResponseEntity<Notification>(notification, HttpStatus.CREATED);

	}

	@PatchMapping(value = "/notifications")
	public ResponseEntity<Notification> updateNotification(@RequestBody Notification requestNotification) {
		Notification notification = notificationService.updateNotification(requestNotification);
		return new ResponseEntity<Notification>(notification, HttpStatus.OK);

	}

	@ApiOperation(value = "Delete Notification", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@DeleteMapping(value = "/notifications")
	public ResponseEntity<Long> deleteNotification(@RequestParam(name = "id", required = true) Long id) throws Exception {
		Long deletedNotification = notificationService.deleteNotification(id);
		return new ResponseEntity<Long>(deletedNotification, HttpStatus.OK);

	}

}

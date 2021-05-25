package com.outofoffice.notificationsservice.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.outofoffice.notificationsservice.requestobjects.NotificationTypeRequest;
import com.outofoffice.notificationsservice.service.NotificationsTypeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/")
public class NotificationsTypeController {
	private final NotificationsTypeService notificationsTypeService;
	final static Logger logger = LoggerFactory.getLogger(NotificationsTypeController.class);
	public NotificationsTypeController(NotificationsTypeService NotificationTypeService) {
		super();
		this.notificationsTypeService = NotificationTypeService;
	}

	@PostMapping(value = "/notificationsType")
	public ResponseEntity<?> insertNotificationsType(@Validated @RequestBody  NotificationTypeRequest requestNotificationType, Errors errors) {
		return notificationsTypeService.insertNotificationsType(requestNotificationType);
	}

	// getting all notification_types!!
	@GetMapping("/all_notification_types")
	public ResponseEntity<?> getNotifications() {
		return notificationsTypeService.getNotificationTypeList();
	}

	@PatchMapping(value = "/notification_type/{notification_typeId}")
	public ResponseEntity<?> updateNotification_type(
			@Validated @RequestBody NotificationTypeRequest requestNotification,
			@PathVariable Long notification_typeId, Errors errors) {
		return notificationsTypeService.updateNotification_type(requestNotification, notification_typeId);
	}

	@ApiOperation(value = "Delete Notification", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@DeleteMapping(value = "/notification_type/{notification_typeId}")
	public ResponseEntity<?> deleteNotificationType(@PathVariable Long notification_typeId) {
		return notificationsTypeService.deleteNotificationType(notification_typeId);
	}

}

package com.outofoffice.notificationsservice.rest;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.outofoffice.notificationsservice.requestobjects.NotificationRequest;
import com.outofoffice.notificationsservice.requestobjects.NotificationTypeRequest;
import com.outofoffice.notificationsservice.service.NotificationsTypeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/")
public class NotificationsTypeController {

	private final NotificationsTypeService notificationsTypeService;

	public NotificationsTypeController(NotificationsTypeService NotificationTypeService) {
		this.notificationsTypeService = NotificationTypeService;
	}

	@PostMapping(value = "/notificationsType")
	public ResponseEntity<?> insertNotificationsType(
			@RequestBody @ModelAttribute @Valid NotificationTypeRequest requestNotificationType, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity<>(new Error("Invalid data input!"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return notificationsTypeService.insertNotificationsType(requestNotificationType);
	}

	// getting all notification_types!!
	@GetMapping("/all_notification_types")
	public ResponseEntity<?> getNotifications() {
		return notificationsTypeService.getNotificationTypeList();
	}

	@PatchMapping(value = "/notification_type/update/{notification_typeId}")
	public ResponseEntity<?> updateNotification_type(
			@RequestBody @ModelAttribute @Valid NotificationTypeRequest requestNotification,
			@PathVariable Long notification_typeId, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity<>(new Error("Invalid data input!"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return notificationsTypeService.updateNotification_type(requestNotification, notification_typeId);
	}

	@ApiOperation(value = "Delete Notification", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@DeleteMapping(value = "/notification_type/delete/{notification_typeId}")
	public ResponseEntity<?> deleteNotificationType(@PathVariable Long notification_typeId) {
		return notificationsTypeService.deleteNotificationType(notification_typeId);
	}

}

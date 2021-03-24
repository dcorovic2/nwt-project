package com.outofoffice.notificationsservice.rest;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.outofoffice.notificationsservice.service.NotificationService;
import com.outofoffice.notificationsservice.requestobjects.NotificationRequest;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/")
public class NotificationsController {

	private final NotificationService notificationService;

	public NotificationsController(NotificationService NotificationService) {
		this.notificationService = NotificationService;
	}

//	@PostMapping(value = "/bulkNotificationCreate")
//	public ResponseEntity<List<Notification>> bulkInsert(@RequestBody List<Notification> requestNotifications) {
//		List<Notification> notifications = notificationService.insertBulkNotifications(requestNotifications);
//		return new ResponseEntity<List<Notification>>(notifications, HttpStatus.CREATED);
//
//	}

	@PostMapping(value = "/notification/create/{employeeId}/{notificationTypeId}")
	public ResponseEntity<?> insertNotification(
			@RequestBody @Valid NotificationRequest requestNotification,
			@PathVariable @NotBlank Long employeeId, @PathVariable @NotBlank Long notificationTypeId, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity<>(new Error("Invalid data input!"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return notificationService.insertNotification(requestNotification, employeeId, notificationTypeId);
	}

	// getting all notifications!!
	@GetMapping("/all_notifications")
	public ResponseEntity<?> getNotifications() {
		return notificationService.getNotificationList();
	}

	@GetMapping("/notification/{notificationId}")
	public ResponseEntity<?> getNotificationById(@PathVariable long notificationId) {
		return notificationService.getNotificationById(notificationId);
	}

//	@GetMapping("/notifications/{employeeId}")
//	public ResponseEntity<?> getNotificationByEmployeeId(@PathVariable long employeeId){
//		  return  notificationService.getNotificationByEmployeeId(employeeId);
//	}

	@PatchMapping(value = "/notifications/update/{notificationId}")
	public ResponseEntity<?> updateNotification(@RequestBody NotificationRequest requestNotification,
			@PathVariable long notificationId) {
		return notificationService.updateNotification(requestNotification, notificationId);
	}

	@ApiOperation(value = "Delete Notification", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@DeleteMapping(value = "/notifications/delete/{notificationsId}")
	public ResponseEntity<?> deleteNotification(@PathVariable Long notificationsId) {
		return notificationService.deleteNotification(notificationsId);
	}

}

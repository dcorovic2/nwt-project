package com.outofoffice.notificationsservice.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.outofoffice.notificationsservice.service.NotificationService;
import com.outofoffice.notificationsservice.requestobjects.NotificationRequest;
import com.outofoffice.notificationsservice.responseobjects.NotificationResponse;

import io.swagger.annotations.ApiOperation;

@RestController
@Validated
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

	@PostMapping(value = "/notification/{employeeId}/{notificationTypeId}")
	public ResponseEntity<?> insertNotification(@Valid @RequestBody NotificationRequest requestNotification,
			@PathVariable Long employeeId, @PathVariable Long notificationTypeId, Errors errors) {
		return notificationService.insertNotification(requestNotification, employeeId, notificationTypeId);
	}

	@PostMapping(value = "/notifications/{holidayTypeId}")
	public ResponseEntity<?> insertNotificationsForHoliday(@PathVariable Long holidayTypeId) {
		return notificationService.insertNotificationsForHoliday(holidayTypeId);
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

	@GetMapping("/notifications/{employeeId}")
	public ResponseEntity<?> getNotificationByEmployeeId(@PathVariable long employeeId) {
		return notificationService.getLastNotificationsByEmployeeId(employeeId, 0);
	}

	@PatchMapping(value = "/notifications/{notificationId}")
	public ResponseEntity<?> updateNotification(
			 @Min(value = 0, message = "Dismiss must be between 0 or 1") @Max(value = 1, message = "Dismiss must be between 0 or 1") 
			 @RequestParam int dismiss,
			@PathVariable long notificationId) {
		return notificationService.updateNotification(dismiss, notificationId);
	}

	@ApiOperation(value = "Delete Notification", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@DeleteMapping(value = "/notifications/{notificationsId}")
	public ResponseEntity<?> deleteNotification(@PathVariable Long notificationsId) {
		return notificationService.deleteNotification(notificationsId);
	}

}

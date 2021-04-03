package com.outofoffice.holidayservice.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.outofoffice.holidayservice.errorhandling.NotValidParamException;
import com.outofoffice.holidayservice.requestobjects.NotificationRequest;
import com.outofoffice.holidayservice.service.NotificationService;

@RestController
@RequestMapping(value = "/")
public class NotificationController {
	private final NotificationService notificationService;

	public NotificationController(NotificationService notificationService) {
		this.notificationService = notificationService;
	}
	
	@PostMapping(value = "/notification/{employeeId}")
	public ResponseEntity<?> insertNotification(@RequestBody @Validated NotificationRequest requestNotification, @PathVariable long employeeId, Errors errors) {
		return notificationService.insertNotification(requestNotification, employeeId);
	}
	
}

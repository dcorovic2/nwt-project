package com.outofoffice.holidayservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.outofoffice.holidayservice.errorhandling.NotFoundException;
import com.outofoffice.holidayservice.model.Employee;
import com.outofoffice.holidayservice.model.Notification;
import com.outofoffice.holidayservice.repository.EmployeeRepository;
import com.outofoffice.holidayservice.repository.NotificationRepository;
import com.outofoffice.holidayservice.requestobjects.NotificationRequest;

@Service
public class NotificationService {
	private final NotificationRepository notificationRepository;
	private final EmployeeRepository employeeRepository;

	public NotificationService(NotificationRepository notificationRepository, EmployeeRepository employeeRepository) {
		this.notificationRepository =  notificationRepository;	
		this.employeeRepository = employeeRepository;
	}
	
	public ResponseEntity<?> insertNotification(NotificationRequest notification, long employeeId) {
		String idString = employeeId + "";
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new NotFoundException(idString, "Employee", "ID", ""));
			
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(employee);
			
		Notification notification2 = new Notification(notification.getText(), employeeList);
		Notification newnoti = notificationRepository.save(notification2);
			
		return new ResponseEntity<>(newnoti, HttpStatus.OK);
	}
	
}

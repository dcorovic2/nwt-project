package com.outofoffice.notificationsservice.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.outofoffice.notificationsservice.errorhandling.ApiError;
import com.outofoffice.notificationsservice.errorhandling.NoDataException;
import com.outofoffice.notificationsservice.errorhandling.NotFoundException;
import com.outofoffice.notificationsservice.errorhandling.RestTemplateResponseErrorHandler;
import com.outofoffice.notificationsservice.model.Employee;
import com.outofoffice.notificationsservice.model.Notification;
import com.outofoffice.notificationsservice.model.NotificationsType;
import com.outofoffice.notificationsservice.repository.NotificationsRepository;
import com.outofoffice.notificationsservice.repository.NotificationsTypeRepository;
import com.outofoffice.notificationsservice.requestobjects.NotificationRequest;
import com.outofoffice.notificationsservice.responseobjects.NotificationResponse;

@Service
public class NotificationService {
	@Autowired
    private RestTemplate restTemplate;
	private String uri = "http://holiday-service/getlistofemployees/";
	private String uriEmployee = "http://employee-service/getAllEmployeesByIds/";
	
	private final NotificationsRepository notificationRepository;
	private final NotificationsTypeRepository notificationtypeRepository;
	private final NotificationsTypeService notificationTypeService;
	private final EmployeeService employeeService;
	
	public NotificationService(NotificationsRepository NotificationsRepository,
			NotificationsTypeService notificationTypeService, EmployeeService employeeService, NotificationsTypeRepository notificationtypeRepository) {
		this.notificationRepository = NotificationsRepository;
		this.notificationTypeService = notificationTypeService;
		this.employeeService = employeeService;
		this.notificationtypeRepository = notificationtypeRepository;
	
	}

//	public List<Notification> insertBulkNotifications(List<Notification> notifications) {
//		List<Notification> insertedNotifications = new ArrayList<Notification>();
//		notifications.forEach(notification ->  {
//			insertedNotifications.add(insertNotification(notification));
//		});
//		
//		return insertedNotifications;
//	}

	public ResponseEntity<?> insertNotification(NotificationRequest notif, Long employeeId, Long notificationTypeID) {
		Employee employee = employeeService.GetEmployeeById(employeeId);
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(employee);
		NotificationsType notificationType = notificationTypeService.getById(notificationTypeID);
		OffsetDateTime date = OffsetDateTime.now();
		Notification notification = new Notification(date, notif.getDepartmentId(), notif.getText(), notificationType,
				employeeList, notif.getDismiss());
		Notification newnotif = notificationRepository.save(notification);
		return new ResponseEntity<>(newnotif, HttpStatus.OK);
	}

	public ResponseEntity<?> getNotificationList() {
		List<Notification> notificationList = notificationRepository.findAll();
		if (notificationList.isEmpty())
			throw new NoDataException();
		else
			return new ResponseEntity<>(notificationList, HttpStatus.OK);
	}

	public ResponseEntity<?> getNotificationById(long notificationID) {
		String id_string = notificationID + "";
		Notification notification = notificationRepository.findById(notificationID)
				.orElseThrow(() -> new NotFoundException(id_string, "Notification", "ID", ""));
		return new ResponseEntity<>(notification, HttpStatus.OK);

	}

	public ResponseEntity<?> getLastNotificationsByEmployeeId(long employeeID, int dismiss) {
		List<Notification> notificationlist = notificationRepository
				.findByEmployeesIdAndDismissOrderByCreateDateDesc(employeeID, dismiss);
		if (notificationlist.isEmpty())
			throw new NoDataException();
		else
			return new ResponseEntity<>(notificationlist, HttpStatus.OK);

	}

	public ResponseEntity<?> updateNotification(int dismiss, long id) {
		String id_string = id + " ";
		Notification updateNotification = notificationRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(id_string, "Notification", "ID", ""));
		updateNotification.setDismiss(dismiss);
		Notification upnotif = notificationRepository.save(updateNotification);
		return new ResponseEntity<>(upnotif, HttpStatus.OK);
	}

	public ResponseEntity<?> deleteNotification(Long id) {
		String id_string = id + " ";
		Notification notificationForDelete = notificationRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(id_string, "Notification", "ID", ""));
		notificationRepository.delete(notificationForDelete);
		Long deleteNotif = notificationForDelete.getId();
		return new ResponseEntity<Long>(deleteNotif, HttpStatus.OK);
	}

	public ResponseEntity<?>  insertNotificationsForHoliday(Long holidayTypeId) {
		try {
		uri = uri + holidayTypeId;
		System.out.println("prije");
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		NotificationResponse  response = restTemplate.getForObject(uri, NotificationResponse.class);
		NotificationsType holidaynotificationtype = new NotificationsType("H",response.getText(),response.getText());
		notificationtypeRepository.save(holidaynotificationtype);
		
		System.out.print(response.getId());
		uriEmployee = uriEmployee + response.getId();
		Employee [] employees = restTemplate.getForObject(uriEmployee, Employee[].class);
		
		System.out.print(employees[0]);
		
		for (long num : response.getId()) {
		    System.out.println("Employee id " + num);
		}
		return null;
		}
		catch (ResourceAccessException e) {
			ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,"Could not connect to:" + uri , "Connection refused!", OffsetDateTime.now());
			return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	    }
	}

}

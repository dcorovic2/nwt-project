package com.outofoffice.notificationsservice.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.outofoffice.notificationsservice.repository.EmployeeRepository;
import com.outofoffice.notificationsservice.repository.NotificationsRepository;
import com.outofoffice.notificationsservice.repository.NotificationsTypeRepository;
import com.outofoffice.notificationsservice.requestobjects.NotificationRequest;
import com.outofoffice.notificationsservice.responseobjects.NotificationResponse;

@Service
public class NotificationService {
	@Autowired
	private RestTemplate restTemplate;
	private String uri = "http://holiday-service/getlistofemployees/1";
	private String uriEmployee = "http://employee-service/getAllEmployeesByIds/";

	@Autowired
	private DiscoveryClient discoveryClient;

	private final NotificationsRepository notificationRepository;
	private final NotificationsTypeRepository notificationtypeRepository;
	private final NotificationsTypeService notificationTypeService;
	private final EmployeeService employeeService;
	private final EmployeeRepository employeeRepo;

	public NotificationService(NotificationsRepository NotificationsRepository,
			NotificationsTypeService notificationTypeService, EmployeeService employeeService,
			NotificationsTypeRepository notificationtypeRepository, EmployeeRepository employeeRepo) {
		this.notificationRepository = NotificationsRepository;
		this.notificationTypeService = notificationTypeService;
		this.employeeService = employeeService;
		this.notificationtypeRepository = notificationtypeRepository;
		this.employeeRepo = employeeRepo;
	}

//	public List<Notification> insertBulkNotifications(List<Notification> notifications) {
//		List<Notification> insertedNotifications = new ArrayList<Notification>();
//		notifications.forEach(notification ->  {
//			insertedNotifications.add(insertNotification(notification));
//		});
//		
//		return insertedNotifications;
//	}

//	public ResponseEntity<?> insertNotification(NotificationRequest notif, Long employeeId, Long notificationTypeID) {
//
//		Employee employee = employeeService.GetEmployeeById(employeeId);
//		List<Employee> employeeList = new ArrayList<>();
//		employeeList.add(employee);
//		NotificationsType notificationType = notificationTypeService.getById(notificationTypeID);
//		OffsetDateTime date = OffsetDateTime.now();
//		Notification notification = new Notification(date, notif.getDepartmentId(), notif.getText(), notificationType,
//				employeeList, notif.getDismiss());
//		Notification newnotif = notificationRepository.save(notification);
//		return new ResponseEntity<>(newnotif, HttpStatus.OK);
//	}

	public Notification insertNotificationForRequest(Long requestId, Long employeeId, Long notificationTypeId,
			String reason) {
		try {
			Employee employee = new Employee();
			NotificationsType notifType = new NotificationsType();
			employee = employeeService.GetEmployeeById(employeeId);
			Employee employeeName = new Employee();
			employeeName = employeeService.GetEmployeeById(2);
			notifType = notificationTypeService.getById(notificationTypeId);
			List<Employee> employeeList = new ArrayList<>();
			employeeList.add(employee);

			String text = "Your request with ID: " + requestId + " is " + notifType.getDisplayName()
					+ " by admin with reason: " + reason + "!";

			Notification notification = new Notification(OffsetDateTime.now(), employee.getDepartment_id(), text,
					notifType,employeeList, requestId,employeeName.getFirstNameLastName(),null);
			Notification newnotif = notificationRepository.save(notification);
			return newnotif;
		} catch (Exception e) {
			new NotFoundException();
			return null;
		}
	}
	
	public Notification insertNotificationForAdmin(Long employeeId, String comment,Long requestId, String displayName) {
		try {
			Employee employee = new Employee();
			NotificationsType notifType = new NotificationsType();
			employee = employeeService.GetEmployeeById(2);
			//System.out.println("ADMINA NADJE: " + employee.getFirstNameLastName());
			Employee employeeName = new Employee();
			employeeName = employeeService.GetEmployeeById(employeeId);
			//System.out.println("RADNIKA NADJE: " + employeeName.getFirstNameLastName());
			notifType = notificationTypeService.getById(1);
			List<Employee> employeeList = new ArrayList<>();
			employeeList.add(employee);

			String text = "New request created: " + comment;

			Notification notification = new Notification(OffsetDateTime.now(), employee.getDepartment_id(), text,
					notifType,employeeList, requestId,employeeName.getFirstNameLastName(),displayName);
			Notification newnotif = notificationRepository.save(notification);
			return newnotif;
		} catch (Exception e) {
			new NotFoundException();
			return null;
		}
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

	public ResponseEntity<?> insertNotificationsForHoliday(Long holidayTypeId) {
		try {
//			List<ServiceInstance> instances = discoveryClient.getInstances("ZUUL-SERVICE");
//			ServiceInstance serviceInstance = instances.get(0);
//			System.out.print("ISPIIIIIIIIIIIIISUJEEEEEE:" + serviceInstance);
//			
//			String baseUrl = serviceInstance.getUri().toString();
//			System.out.print(baseUrl);
//			baseUrl = baseUrl + "/holiday/getlistofemployees/";

			uri = uri + holidayTypeId;
			restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
			NotificationResponse response = restTemplate.getForObject(uri, NotificationResponse.class);
			NotificationsType holidaynotificationtype = new NotificationsType("H", response.getText(),
					response.getText());
			notificationtypeRepository.save(holidaynotificationtype);

			List<Long> ids = response.getId();
			Employee[] employees = restTemplate.postForObject(uriEmployee, ids, Employee[].class);
			Employee employeeName = new Employee();
			employeeName = employeeService.GetEmployeeById(2);
			for (Employee employee : employees) {
				OffsetDateTime date = OffsetDateTime.now();
				List<Employee> empl = new ArrayList<>();
				empl.add(employee);
				employeeRepo.save(employee);
				Notification notification1 = new Notification(date, employee.getDepartment_id(), response.getText(),
						holidaynotificationtype, empl, 0,0L,employeeName.getFirstNameLastName(),null);
				notificationRepository.save(notification1);
			}
			return null;
		} catch (ResourceAccessException e) {
			ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Could not connect to:" + uri,
					"Connection refused!", OffsetDateTime.now());
			return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
		} catch (IllegalStateException e) {
			ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Could not connect to:" + uri,
					"Connection refused!", OffsetDateTime.now());
			return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
		}

	}

	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}
}

package com.outofoffice.notificationsservice.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.outofoffice.notificationsservice.errorhandling.NoDataException;
import com.outofoffice.notificationsservice.errorhandling.NotFoundException;

import com.outofoffice.notificationsservice.model.Employee;
import com.outofoffice.notificationsservice.model.Notification;
import com.outofoffice.notificationsservice.model.NotificationsType;
import com.outofoffice.notificationsservice.repository.NotificationsRepository;
import com.outofoffice.notificationsservice.requestobjects.NotificationRequest;

@Service
public class NotificationService {

	private final NotificationsRepository notificationRepository;
	private final NotificationsTypeService notificationTypeService;
	private final EmployeeService employeeService;

	public NotificationService(NotificationsRepository NotificationsRepository,
			NotificationsTypeService notificationTypeService, EmployeeService employeeService) {
		this.notificationRepository = NotificationsRepository;
		this.notificationTypeService = notificationTypeService;
		this.employeeService = employeeService;
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

}

package com.outofoffice.notificationsservice.service;


import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import com.outofoffice.notificationsservice.error.ErrorMessage;
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
	private final ErrorMessage errorMessage =  new ErrorMessage("Something went wrong!");
	
	public NotificationService(NotificationsRepository NotificationsRepository,NotificationsTypeService notificationTypeService,EmployeeService employeeService) {
		this.notificationRepository =  NotificationsRepository;
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
		try {
			Employee employee = employeeService.GetEmployeeById(employeeId);
			if (employee==null) {
				 return new ResponseEntity<>( new ErrorMessage("Employee with given id does not exist"),HttpStatus.NOT_FOUND);
			}
			List<Employee> employeeList = new ArrayList<>();
			employeeList.add(employee);
			NotificationsType notificationType = notificationTypeService.getById(notificationTypeID);
			if (notificationType==null) {
				 return new ResponseEntity<>( new ErrorMessage("Notification Type with given id does not exit!"),HttpStatus.NOT_FOUND);
			}
			OffsetDateTime date = OffsetDateTime.now();
			Notification notification = new Notification(date,notif.getDepartmentId(),notif.getText(),notificationType,employeeList);	
			Notification newnotif = notificationRepository.save(notification);
			
			return new ResponseEntity<>(newnotif, HttpStatus.OK);
		 } catch (Exception e) {
	        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	    }  
	}
	
	public ResponseEntity<?> getNotificationList() {
      try {
            List<Notification> notificationList = notificationRepository.findAll();
            return new ResponseEntity<>(notificationList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	public ResponseEntity<?> getNotificationById(long notificationID) {
        try {
        	Notification notification = notificationRepository.findById(notificationID).get();
            if(notification == null)
                return new ResponseEntity<>(new ErrorMessage("No notification found!"),HttpStatus.NOT_FOUND);
            
            return new ResponseEntity<>(notification, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("No notification found!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	
	
//	public ResponseEntity<?> getNotificationByEmployeeId(long employeeID) {
//        try {
//        	Employee employee = employeeService.GetEmployeeById(employeeID);
//        	List<Notification> notificationlist = notificationRepository.findById(employeeID, PageRequest.of(0, 5, Sort.by("createDate").descending()));
//        	return new ResponseEntity<>(notificationlist, HttpStatus.OK);
//        	if(employee == null)
//                return new ResponseEntity<>(new ErrorMessage("No employee found!"),HttpStatus.NOT_FOUND);
//            return new ResponseEntity<>(employee, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

	
	public ResponseEntity<?> updateNotification(NotificationRequest notification, long id){
	    try {
	    	Notification updateNotification = notificationRepository.findById(id).get();
	    	updateNotification.setDepartmentId(notification.getDepartmentId());
	    	updateNotification.setText(notification.getText());
	    	Notification upnotif = notificationRepository.save(updateNotification);
	    	
	    	return new ResponseEntity<>(upnotif, HttpStatus.OK);
	    } catch (Exception e) {
	    	  return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	
	public ResponseEntity<?> deleteNotification(Long id) {
		try {
		final Optional<Notification> notificationForDelete =  notificationRepository.findById(id);
		if(notificationForDelete.isEmpty()) {
			return new ResponseEntity<>(new ErrorMessage("Notification with given id does not exit!"),HttpStatus.NOT_FOUND);
		}
		notificationRepository.delete(notificationForDelete.get());
		Long deleteNotif = notificationForDelete.get().getId();	
		return new ResponseEntity<Long>(deleteNotif, HttpStatus.OK);
		
		} catch (Exception e) {
	    	  return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
      }
	}
	
}

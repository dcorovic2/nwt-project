package com.outofoffice.notificationsservice.service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.outofoffice.notificationsservice.error.ErrorMessage;
import com.outofoffice.notificationsservice.model.Employee;
import com.outofoffice.notificationsservice.model.Holiday;
import com.outofoffice.notificationsservice.model.Notification;
import com.outofoffice.notificationsservice.model.NotificationsType;
import com.outofoffice.notificationsservice.repository.HolidayRepository;
import com.outofoffice.notificationsservice.requestobjects.EmployeeRequest;
import com.outofoffice.notificationsservice.requestobjects.HolidayRequest;
import com.outofoffice.notificationsservice.requestobjects.NotificationRequest;


@Service
public class HolidayService {
	private final HolidayRepository holidayRepository;
	private final EmployeeService employeeService;
	private final ErrorMessage errorMessage =  new ErrorMessage("Something went wrong!");
	
	public HolidayService(HolidayRepository holidayRepository, EmployeeService employeeService) {
		super();
		this.holidayRepository = holidayRepository;
		this.employeeService = employeeService;
	}
	
	public ResponseEntity<?> insertHoliday(HolidayRequest holiday, Long employeeid) {
		try {
			Employee employee = employeeService.GetEmployeeById(employeeid);
			if (employee==null) {
				 return new ResponseEntity<>( new ErrorMessage("Employee with given id does not exist"),HttpStatus.NOT_FOUND);
			}
			List<Employee> employeeList = new ArrayList<>();
			employeeList.add(employee);	
			Holiday holi = new Holiday(holiday.getDisplayName(),holiday.getName(),holiday.getType(),employeeList);
			Holiday newholi = holidayRepository.save(holi);
			return new ResponseEntity<>(newholi, HttpStatus.OK);
		}catch (Exception e) {
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
}

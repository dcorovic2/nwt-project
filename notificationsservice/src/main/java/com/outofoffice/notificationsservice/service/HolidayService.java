package com.outofoffice.notificationsservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.outofoffice.notificationsservice.model.Employee;
import com.outofoffice.notificationsservice.model.Holiday;
import com.outofoffice.notificationsservice.repository.HolidayRepository;
import com.outofoffice.notificationsservice.requestobjects.HolidayRequest;

@Service
public class HolidayService {
	private final HolidayRepository holidayRepository;
	private final EmployeeService employeeService;

	public HolidayService(HolidayRepository holidayRepository, EmployeeService employeeService) {
		super();
		this.holidayRepository = holidayRepository;
		this.employeeService = employeeService;
	}

	public ResponseEntity<?> insertHoliday(HolidayRequest holiday, Long employeeid) {
			Employee employee = employeeService.GetEmployeeById(employeeid);
			List<Employee> employeeList = new ArrayList<>();
			employeeList.add(employee);
			Holiday holi = new Holiday(holiday.getDisplayName(), holiday.getName(), holiday.getType(), employeeList);
			Holiday newholi = holidayRepository.save(holi);
			return new ResponseEntity<>(newholi, HttpStatus.OK);
	}

}

package com.outofoffice.holidayservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.outofoffice.holidayservice.errorhandling.NotFoundException;
import com.outofoffice.holidayservice.model.Employee;
import com.outofoffice.holidayservice.model.Holiday;
import com.outofoffice.holidayservice.model.HolidayType;
import com.outofoffice.holidayservice.repository.EmployeeRepository;
import com.outofoffice.holidayservice.repository.HolidayRepository;
import com.outofoffice.holidayservice.repository.HolidayTypeRepository;
import com.outofoffice.holidayservice.requestobjects.HolidayRequest;

@Service
public class HolidayService {
	private final HolidayRepository holidayRepository;
	private final EmployeeRepository employeeRepository;
	private final HolidayTypeRepository holidayTypeRepository;

	public HolidayService(HolidayRepository holidayRepository, EmployeeRepository employeeRepository, HolidayTypeRepository holidayTypeRepository) {
		this.holidayRepository =  holidayRepository;	
		this.employeeRepository = employeeRepository;
		this.holidayTypeRepository = holidayTypeRepository;
	}
	
	public ResponseEntity<?> insertHoliday(HolidayRequest holiday, long notificationTypeID, long employeeId) {
		String idStringNotification = notificationTypeID + "";
		String idEmployeeNotification = employeeId + "";
		
		HolidayType holidayType = holidayTypeRepository.findById(notificationTypeID)
				.orElseThrow(() -> new NotFoundException(idStringNotification, "HoldayType", "ID", ""));
		
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new NotFoundException(idEmployeeNotification, "Employee", "ID", ""));
		
			
		List<Employee> listEmployee = new ArrayList<>();
		listEmployee.add(employee);
		Holiday holiday2 = new Holiday(holiday.getStartDate(), holiday.getEndDate(), listEmployee, holidayType);
		Holiday newholiday = holidayRepository.save(holiday2);
		
		return new ResponseEntity<>(newholiday, HttpStatus.OK);
	}

	public ResponseEntity<?> updateHoliday(HolidayRequest holiday, long id) {
		String tmp = id + "";
		Holiday updatedHoliday =  holidayRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(tmp, "Holiday", "ID", ""));
			
		updatedHoliday.setStartDate(holiday.getStartDate());
		updatedHoliday.setEndDate(holiday.getEndDate());
		Holiday upholy = holidayRepository.save(updatedHoliday);
		
		return new ResponseEntity<>(upholy, HttpStatus.OK);		
	}
	
	public ResponseEntity<?> deleteHoliday(Long id) {
		String tmp = id + "";
		Holiday holidayForDelete =  holidayRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(tmp, "Holiday", "ID", ""));
			
		holidayRepository.delete(holidayForDelete);
		Long deletedHoliday = holidayForDelete.getId();
			
		return new ResponseEntity<Long>(deletedHoliday, HttpStatus.OK);
	}

	public ResponseEntity<?> getHolidays(long employeeId) {
		String tmp = employeeId + "";
		List<Holiday> holidayList = holidayRepository.findAll();
		List<Holiday> returnList = new ArrayList<>();
			
		for(Holiday x: holidayList) {
			List<Employee> employeeList = x.getEmployees();
			for(Employee e: employeeList) {
				if(e.getId() == employeeId) {
					returnList.add(x);
				}
			}
		}
			
		if (returnList.isEmpty()) {
			throw new NotFoundException(tmp, "Holiday for given employee", "ID", "");
		}
			
		return new ResponseEntity<>(returnList, HttpStatus.OK);
	}

}

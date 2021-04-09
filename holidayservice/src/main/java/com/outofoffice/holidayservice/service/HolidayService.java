package com.outofoffice.holidayservice.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
import com.outofoffice.holidayservice.responseobjects.HolidayResponse;
import com.outofoffice.holidayservice.responseobjects.NotificationResponse;

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
	
	public ResponseEntity<?> updateListHoliday(long holidayTypeID, long employeeId, String firstAndLastName) {
		String idStringNotification = holidayTypeID + "";
		String idEmployeeNotification = employeeId + "";
		
		HolidayType holidayType = holidayTypeRepository.findById(holidayTypeID)
				.orElseThrow(() -> new NotFoundException(idStringNotification, "HoldayType", "ID", ""));
		
		Employee employee = new Employee(employeeId, firstAndLastName);
		employeeRepository.save(employee);
		
		Holiday holidayGet = holidayRepository.findByHolidayTypeId(holidayTypeID);
		holidayGet.getEmployees().add(employee);
		Holiday upholy = holidayRepository.save(holidayGet);
		
		return new ResponseEntity<>(upholy, HttpStatus.OK);	
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

	public ResponseEntity<?> insertDefaultHoliday(HolidayRequest holiday, long holidayTypeID, List<HolidayResponse> sourceList) {
		String idStringNotification = holidayTypeID + "";
		
		HolidayType holidayType = holidayTypeRepository.findById(holidayTypeID)
				.orElseThrow(() -> new NotFoundException(idStringNotification, "HoldayType", "ID", ""));
		
		List<Employee> employeeList = new ArrayList();
		for(HolidayResponse x: sourceList) {
			Employee employee = new Employee(x.getId(), x.getName());
			Employee newemp = employeeRepository.save(employee);
			
			employeeList.add(newemp);
		}
	
		Holiday holiday2 = new Holiday(holiday.getStartDate(), holiday.getEndDate(), employeeList, holidayType);
		Holiday newholiday = holidayRepository.save(holiday2);
		
		return new ResponseEntity<>(newholiday, HttpStatus.OK);
	}

	public ResponseEntity<?> getDaysNumOfHoliday(LocalDate startDate, int daysNum) {
		LocalDate endDate = startDate.plusDays(daysNum);
		
		List<Holiday> holidays = holidayRepository.findAll();
		
		int returnDaysNum = 0;
		
		for(Holiday x: holidays) {
			if(x.getStartDate().isAfter(startDate) && x.getStartDate().isBefore(endDate)) {
				returnDaysNum += ChronoUnit.DAYS.between(x.getEndDate(), x.getStartDate());
			}
				
		}
		
		return new ResponseEntity<>(returnDaysNum, HttpStatus.OK);
	}

	public ResponseEntity<?> createHoliday(HolidayRequest requestHoliday, long holidayTypeID) {
		String idStringNotification = holidayTypeID + "";
		
		HolidayType holidayType = holidayTypeRepository.findById(holidayTypeID)
				.orElseThrow(() -> new NotFoundException(idStringNotification, "HoldayType", "ID", ""));
		
		List<Employee> listEmployee = new ArrayList<>();
		Holiday holiday2 = new Holiday(requestHoliday.getStartDate(), requestHoliday.getEndDate(), listEmployee, holidayType);
		Holiday newholiday = holidayRepository.save(holiday2);
		
		return new ResponseEntity<>(newholiday, HttpStatus.OK);
	}

	public ResponseEntity<?> getAllEmployees(long holidayTypeId) {
		
		Holiday holiday = holidayRepository.findHolidayByHolidayTypeId(holidayTypeId);
		String text = holidayTypeRepository.getOne(holidayTypeId).getDisplayName();
		List<Employee> employeeList = holiday.getEmployees();
		List<Long> listaIndeksa = new ArrayList();
		
		for(Employee emp: employeeList) {
			listaIndeksa.add(emp.getId());
		}
				
		NotificationResponse notificationResponse = new NotificationResponse(listaIndeksa, text);
		
		return new ResponseEntity<>(notificationResponse, HttpStatus.OK);
	}

}

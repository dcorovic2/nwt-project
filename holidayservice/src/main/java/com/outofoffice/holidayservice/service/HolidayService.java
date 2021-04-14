package com.outofoffice.holidayservice.service;

import java.net.ConnectException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.outofoffice.holidayservice.errorhandling.ApiError;
import com.outofoffice.holidayservice.errorhandling.NoDataException;
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
	@Autowired
	public RestTemplate restTemplate; 
	
	final String uri = "http://employee-service/getAllEmployeesNames";
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

	public ResponseEntity<?> insertDefaultHoliday(HolidayRequest holiday, long holidayTypeID) {
		try {
			ResponseEntity<HolidayResponse[]> response =  restTemplate.getForEntity(uri, HolidayResponse[].class);
			
			List<HolidayResponse> sourceList = Arrays.asList(response.getBody());
			
			String idStringNotification = holidayTypeID + "";
			
			HolidayType holidayType = holidayTypeRepository.findById(holidayTypeID)
					.orElseThrow(() -> new NotFoundException(idStringNotification, "HoldayType", "ID", ""));
			
			if(sourceList.isEmpty()) {
				throw new NoDataException();
			}
			
			List<Employee> employeeList = new ArrayList();
			for(HolidayResponse x: sourceList) {
				Employee employee = new Employee(x.getId(), x.getName());
				Employee newemp = employeeRepository.save(employee);
				
				employeeList.add(newemp);
			}
		
			Holiday holiday2 = new Holiday(holiday.getStartDate(), holiday.getEndDate(), employeeList, holidayType);
			Holiday newholiday = holidayRepository.save(holiday2);
			
			return new ResponseEntity<>(newholiday, HttpStatus.OK);	
			
		} catch (ResourceAccessException e) {
			ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Could not connect to: " + uri , "Connection refused!", OffsetDateTime.now());
			return new ResponseEntity<>(apiError, apiError.getStatus());
	    } catch(IllegalStateException e) {
	    	ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Could not connect to: " + uri , "Connection refused!", OffsetDateTime.now());
			return new ResponseEntity<>(apiError, apiError.getStatus());
	    }
		
	}

	public ResponseEntity<?> getDaysNumOfHoliday(LocalDate startDate, int daysNum) {
		LocalDate endDate = startDate.plusDays(daysNum);
		
		List<Holiday> holidays = holidayRepository.findAll();
		
		long returnDaysNum = 0;
		
		for(Holiday x: holidays) {
			
			LocalDate holidayStart = x.getStartDate();
			LocalDate holidayEnd = x.getEndDate();
			
			if (holidayStart.isAfter(startDate) && holidayEnd.isBefore(endDate)) {
				returnDaysNum += ChronoUnit.DAYS.between(x.getStartDate(), x.getEndDate());
			} else if (x.getStartDate().equals(startDate) && x.getEndDate().equals(endDate)) {
				returnDaysNum = daysNum;
			} else if (x.getStartDate().equals(startDate) && x.getEndDate().isBefore(endDate)) {
				returnDaysNum += ChronoUnit.DAYS.between(x.getStartDate(), x.getEndDate());
			} else if (x.getStartDate().isAfter(startDate) && x.getEndDate().equals(endDate)) {
				returnDaysNum += ChronoUnit.DAYS.between(x.getStartDate(), x.getEndDate()); 
			} else if (x.getStartDate().equals(startDate) && x.getEndDate().isAfter(endDate)) {
				returnDaysNum += daysNum;
			} else if (x.getStartDate().isAfter(startDate) && x.getStartDate().isBefore(endDate) && x.getEndDate().isAfter(endDate)) { 
				returnDaysNum += ChronoUnit.DAYS.between(x.getStartDate(), endDate); 
			} else if (x.getEndDate().isAfter(startDate) && x.getEndDate().isBefore(endDate) && x.getStartDate().isBefore(startDate)) {
				returnDaysNum += ChronoUnit.DAYS.between(startDate, x.getEndDate()); 
			} else if (x.getEndDate().equals(endDate) && x.getStartDate().isBefore(startDate)) {
				returnDaysNum = daysNum;
			} else if (x.getStartDate().isBefore(startDate) && x.getEndDate().isAfter(endDate)) {
				returnDaysNum = daysNum;
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
		String text = holidayTypeRepository.getOne(holidayTypeId).getText();
		List<Employee> employeeList = holiday.getEmployees();
		List<Long> listaIndeksa = new ArrayList();
		
		for(Employee emp: employeeList) {
			listaIndeksa.add(emp.getId());
		}
				
		NotificationResponse notificationResponse = new NotificationResponse(listaIndeksa, text);
		
		return new ResponseEntity<>(notificationResponse, HttpStatus.OK);
	}

	public ResponseEntity<?> getAllHolidays() {
		List<Holiday> holidays = holidayRepository.findAll();
		
		if(holidays.isEmpty()) {
			throw new NoDataException();
		}
		
		return new ResponseEntity<>(holidays, HttpStatus.OK);
	}

	public ResponseEntity<?> updateHolidayListOfEmployees(Long employeeid) {
		List<Holiday> holidays = holidayRepository.deleteByEmployees(employeeid);
		
//		if(holidays.isEmpty()) {
//			throw new NoDataException();
//		}
//		
//		for(Holiday holiday: holidays) {
//			for(Employee employee: holiday.getEmployees()) {
//				if(employee.getId() == employeeid) {
//					List<Employee> empTmp = holiday.getEmployees();
//					empTmp.remove(employee);
//					holiday.setEmployees(empTmp);
//				}
//			}System.out.println("Izlaso");
//		}
//System.out.println("Izlaso");
//		for(Employee e: holidays.get(0).getEmployees()) {
//			System.out.println(e.getFirstnameLastName());
//		}
//		
//		employeeRepository.deleteById(employeeid);
//		List<Holiday> upholy = (List<Holiday>) holidayRepository.saveAll(holidays);
//		
		return new ResponseEntity<>(holidays, HttpStatus.OK);	
	}

}

package com.outofoffice.holidayservice.rest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.outofoffice.holidayservice.model.Employee;
import com.outofoffice.holidayservice.requestobjects.HolidayRequest;
import com.outofoffice.holidayservice.responseobjects.HolidayResponse;
import com.outofoffice.holidayservice.service.HolidayService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping(value = "/")
public class HolidayController {
	private final HolidayService holidayService;

	
	public HolidayController(HolidayService holidayService) {
		this.holidayService = holidayService;
	}
	
	@ApiOperation(value = "Admin creates a holiday with empty list of employees")
	@PostMapping(value = "/holiday/{holidayTypeID}")
	public ResponseEntity<?> insertHoliday(@RequestBody @Validated HolidayRequest requestHoliday, @PathVariable long holidayTypeID, Errors errors) {
		return holidayService.createHoliday(requestHoliday, holidayTypeID);
	}
	
	@ApiOperation(value = "This holiday applies to all employees")
	@PostMapping(value = "/defaultHoliday/{holidayTypeID}")
	public ResponseEntity<?> insertDefaultHoliday(@RequestBody @Validated HolidayRequest requestHoliday, @PathVariable Long holidayTypeID, Errors errors) {
		return holidayService.insertDefaultHoliday(requestHoliday, holidayTypeID);
	}
	
	@ApiOperation(value = "Update holiday list - the employee chooses a holiday")
	@PatchMapping(value = "/holiday/{holidayTypeID}/{employeeID}/{firstAndLastName}")
	public ResponseEntity<?> updateListHoliday(@PathVariable Long holidayTypeID, @PathVariable Long employeeID, @PathVariable String firstAndLastName) {
		return holidayService.updateListHoliday(holidayTypeID, employeeID, firstAndLastName);
	}
	
	@ApiOperation(value = "Update holiday list - admin modifies data")
	@PatchMapping(value = "/holiday/{holidayId}")
	public ResponseEntity<?> updateHoliday(@RequestBody @Validated HolidayRequest requestHoliday, @PathVariable Long holidayId, Errors errors) {
		return holidayService.updateHoliday(requestHoliday, holidayId);
	}
	
	@ApiOperation(value = "Update holiday - delete employee")
	@PatchMapping(value = "/holidayupdate/{employeeId}")
	public ResponseEntity<?> updateHolidayListOfEmployees(@PathVariable Long employeeId) {
		return holidayService.updateHolidayListOfEmployees(employeeId);
	}
	
	@ApiOperation(value = "Delete holiday using specific holiday id - admin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@DeleteMapping(value = "/holiday/{holidayId}")
	public ResponseEntity<?> deleteHoliday(@PathVariable Long holidayId) {
		return holidayService.deleteHoliday(holidayId);
	}
	
	@ApiOperation(value = "Get list of employees for specific holiday type")
	@GetMapping(value = "/getlistofemployees/{holidayTypeId}")
	public ResponseEntity<?> getAllHolidayEmployeeList(@PathVariable Long holidayTypeId) {
		return holidayService.getAllEmployees(holidayTypeId);
	}
	
	//@ApiOperation(value = "Get list of all holidays")
	@GetMapping(value = "/getlistofholidays")
	public ResponseEntity<?> getListOfHolidays() {
		return holidayService.getAllHolidays();
	}

	@ApiOperation(value = "Check if the holiday is between leave dates")
	@GetMapping(value = "/holiday/{startDate}/{daysNum}")
	public ResponseEntity<?> getDaysNumOfHoliday(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, @PathVariable int daysNum) {
		return holidayService.getDaysNumOfHoliday(startDate, daysNum);
	}
	
}

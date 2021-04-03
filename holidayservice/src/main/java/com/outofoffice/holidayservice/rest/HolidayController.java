package com.outofoffice.holidayservice.rest;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.outofoffice.holidayservice.errorhandling.NotValidParamException;
import com.outofoffice.holidayservice.requestobjects.HolidayRequest;
import com.outofoffice.holidayservice.service.HolidayService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/")
public class HolidayController {
	private final HolidayService holidayService;

	public HolidayController(HolidayService holidayService) {
		this.holidayService = holidayService;
	}
	
	@PostMapping(value = "/holiday/{holidayTypeID}/{employeeID}")
	public ResponseEntity<?> insertHoliday(@RequestBody @Validated HolidayRequest requestHoliday, @PathVariable long holidayTypeID, @PathVariable long employeeID, Errors errors) {
		return holidayService.insertHoliday(requestHoliday, holidayTypeID, employeeID);
	}

	@PatchMapping(value = "/holiday/{holidayId}")
	public ResponseEntity<?> updateHoliday(@RequestBody @Validated HolidayRequest requestHoliday, @PathVariable long holidayId, Errors errors) {
		return holidayService.updateHoliday(requestHoliday, holidayId);
	}
	
	@ApiOperation(value = "Delete Holiday", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@DeleteMapping(value = "/holiday/{holidayId}")
	public ResponseEntity<?> deleteHoliday(@PathVariable Long holidayId) {
		return holidayService.deleteHoliday(holidayId);
	}
	
	@ApiOperation(value = "Find all holidays for employee")
	@GetMapping(value = "/holiday/{employeeId}")
	public ResponseEntity<?> getHoliday(@PathVariable long employeeId) {
		return holidayService.getHolidays(employeeId);
	}
	
}
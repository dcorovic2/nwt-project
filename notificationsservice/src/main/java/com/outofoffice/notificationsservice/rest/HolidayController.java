package com.outofoffice.notificationsservice.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.outofoffice.notificationsservice.requestobjects.HolidayRequest;
import com.outofoffice.notificationsservice.service.HolidayService;


@RestController
@RequestMapping(value = "/")
public class HolidayController {
	private final HolidayService holidayService;

	public HolidayController(HolidayService holidayService) {
		super();
		this.holidayService = holidayService;
	}
	
	@PostMapping(value = "/holiday/create/{employeeId}")
	public ResponseEntity<?> insertHoliday(@RequestBody HolidayRequest requestHoliday, @PathVariable Long employeeId) {
		return holidayService.insertHoliday(requestHoliday, employeeId);
	}
	
	
}

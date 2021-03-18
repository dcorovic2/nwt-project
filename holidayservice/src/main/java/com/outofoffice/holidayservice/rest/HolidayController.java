package com.outofoffice.holidayservice.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.outofoffice.holidayservice.model.Holiday;
import com.outofoffice.holidayservice.service.HolidayService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/")

public class HolidayController {
	private final HolidayService HolidayService;

	public HolidayController(HolidayService HolidayService) {
		this.HolidayService = HolidayService;
	}

	@PostMapping(value = "/bulkHolidayCreate")
	public ResponseEntity<List<Holiday>> bulkInsert(@RequestBody List<Holiday> requestHolidays) {
		List<Holiday> holidays = HolidayService.insertBulkHolidays(requestHolidays);
		return new ResponseEntity<List<Holiday>>(holidays, HttpStatus.CREATED);

	}

	@PostMapping(value = "/holiday")
	public ResponseEntity<Holiday> insertHoliday(@RequestBody Holiday requestHoliday) {
		Holiday holiday = HolidayService.insertHoliday(requestHoliday);
		return new ResponseEntity<Holiday>(holiday, HttpStatus.CREATED);

	}

	@PatchMapping(value = "/holiday")
	public ResponseEntity<Holiday> updateHoliday(@RequestBody Holiday requestHoliday) {
		Holiday holiday = HolidayService.updateHoliday(requestHoliday);
		return new ResponseEntity<Holiday>(holiday, HttpStatus.OK);

	}

	@ApiOperation(value = "Delete Holiday", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@DeleteMapping(value = "/holiday")
	public ResponseEntity<Long> deleteHoliday(@RequestParam(name = "id", required = true) Long id) throws Exception {
		Long deletedHoliday = HolidayService.deleteHoliday(id);
		return new ResponseEntity<Long>(deletedHoliday, HttpStatus.OK);

	}

	@ApiOperation(value = "Find Holiday")
	@GetMapping(value = "/holiday")
	public ResponseEntity<List<Holiday>> getHoliday(@RequestParam(name = "name", required = true) String name)
			throws Exception {
		List<Holiday> holidays = HolidayService.getHolidaysByName(name);
		return new ResponseEntity<List<Holiday>>(holidays, HttpStatus.OK);

	}

}

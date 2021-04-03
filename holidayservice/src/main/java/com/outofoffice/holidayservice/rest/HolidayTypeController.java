package com.outofoffice.holidayservice.rest;

import javax.validation.Valid;

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
import com.outofoffice.holidayservice.requestobjects.HolidayTypeRequest;
import com.outofoffice.holidayservice.service.HolidayTypeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/")
@Validated 
public class HolidayTypeController {
	private final HolidayTypeService holidayTypeService;

	public HolidayTypeController(HolidayTypeService holidayTypeService) {
		this.holidayTypeService = holidayTypeService;
	}
	
	@PostMapping(value = "/holidayType")
	public ResponseEntity<?> insertHolidayType(@RequestBody @Valid HolidayTypeRequest requestHolidayType, Errors errors) {
		return holidayTypeService.insertHolidayType(requestHolidayType);
	}  
	 
	@PatchMapping(value = "/holidayType/{id}")
	public ResponseEntity<?> updateHolidayType(@RequestBody @Validated HolidayTypeRequest requestHolidayType, @PathVariable long id, Errors errors) {	 
		return holidayTypeService.updateHolidayType(requestHolidayType, id);
	}
  
	@ApiOperation(value = "Delete Holiday Type", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@DeleteMapping(value = "/holidayType/{id}")
	public ResponseEntity<?> deleteHolidayType(@PathVariable long id) throws Exception {
		return holidayTypeService.deleteHolidayType(id);
	}
	
	@ApiOperation(value = "List of all holiday types")
	@GetMapping(value = "/listHolidayTypes")
	public ResponseEntity<?> getAllHolidayTypes() {
		return holidayTypeService.getAllHolidays();
	}
}

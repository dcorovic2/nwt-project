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
import com.outofoffice.holidayservice.requestobjects.EmployeeRequest;
import com.outofoffice.holidayservice.service.EmployeeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/")
@Validated
public class EmployeeController {
	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@PostMapping(value = "/employee")
	public ResponseEntity<?> insertEmployee(@RequestBody @Valid EmployeeRequest requestEmployee, Errors errors) {
		return employeeService.insertEmployee(requestEmployee);
	}
	
	@PatchMapping(value = "/employee/{id}")
	public ResponseEntity<?> updateEmployee(@RequestBody @Validated EmployeeRequest requestEmployee, @PathVariable long id, Errors errors) {
		return employeeService.updateEmployee(requestEmployee, id);
	}
	  
	@ApiOperation(value = "Delete employee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@DeleteMapping(value = "/employee/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable long id) {
		return employeeService.deleteEmployee(id);
	}   
	   
	@ApiOperation(value = "Find employee by ID")
	@GetMapping(value = "/employee/getEmployee/{id}")
	public ResponseEntity<?> getEmployee(@PathVariable long id) {
		return employeeService.getEmployeeById(id);
	}
	
	
}

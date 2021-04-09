package com.outofoffice.notificationsservice.rest;

import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.outofoffice.notificationsservice.requestobjects.EmployeeRequest;
import com.outofoffice.notificationsservice.requestobjects.NotificationRequest;
import com.outofoffice.notificationsservice.service.EmployeeService;

import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping(value = "/")
public class EmployeeController {
	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
//	@PostMapping(value = "/employees")
//	public ResponseEntity<?> insertEmployee(@RequestBody EmployeeRequest requestEmployee) {
//		return employeeService.insertEmployee(requestEmployee);
//	}
	
	//getting all employees!!
	@GetMapping("/employees")
	public ResponseEntity<?> getEmployees(){
	    return employeeService.getEmployeeList();
	}
	
	//getting employee by id!!
	@GetMapping("/employees/{employeeId}")
	public ResponseEntity<?> getEmployeeById(@PathVariable long employeeId){
		return employeeService.getEmployeeById(employeeId);
	}
	
	@PatchMapping(value = "/employees/{id}")
	public ResponseEntity<?> updateEmployee(@RequestBody EmployeeRequest requestEmployee, @PathVariable Long id) {
		return employeeService.updateEmployee(requestEmployee, id);
	}
	
	@ApiOperation(value = "Delete Notification", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@DeleteMapping(value = "/employees/{employeeId}")
	public ResponseEntity<?> deleteEmployee(@PathVariable Long employeeId){
		return employeeService.deleteEmployee(employeeId);
	}
	
}

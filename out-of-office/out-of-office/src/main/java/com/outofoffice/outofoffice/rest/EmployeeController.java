package com.outofoffice.outofoffice.rest;

import java.net.http.HttpResponse;
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

import com.outofoffice.outofoffice.model.Employee;
import com.outofoffice.outofoffice.service.EmployeeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/")

public class EmployeeController {
    private final EmployeeService employeeService;
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@PostMapping(value = "/bulkEmployeeCreate")
	public ResponseEntity<List<Employee>> bulkInsert(@RequestBody List<Employee> requestEmployees) {
	List<Employee> employees = employeeService.insertBulkEmployees(requestEmployees);
	return new ResponseEntity<List<Employee>>(employees, HttpStatus.CREATED);
		
	}
	
	@PostMapping(value = "/employee")
	public ResponseEntity<Employee> insertEmployee(@RequestBody Employee requestEmployee) {
	Employee employee = employeeService.insertEmployee(requestEmployee);
	return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
		
	}
	
	@PatchMapping(value = "/employee")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee requestEmployee) {
	Employee employee = employeeService.updateEmployee(requestEmployee);
	return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		
	}
	
	@ApiOperation(value="Delete Employee",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@DeleteMapping(value = "/employee")
	public ResponseEntity<Long> deleteEmployee(@RequestParam(name="id", required = true) Long id) throws Exception{
	Long deletedEmployee = employeeService.deleteEmployee(id);
	return new ResponseEntity<Long>(deletedEmployee, HttpStatus.OK);
		
	}
	
	@ApiOperation(value="Find Employee")
	@GetMapping(value = "/employee")
	public ResponseEntity<List<Employee>> getEmployee(@RequestParam(name="name", required = true) String name) throws Exception{
	List<Employee> employees = employeeService.getEmployeesByName(name);
	return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
		
	}
	
	
    
}

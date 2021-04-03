package com.outofoffice.outofoffice.rest;

import java.net.http.HttpResponse;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.outofoffice.outofoffice.model.Employee;
import com.outofoffice.outofoffice.requestobjects.EmployeeDepartmentChange;
import com.outofoffice.outofoffice.requestobjects.EmployeeRequest;
import com.outofoffice.outofoffice.service.EmployeeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/")

public class EmployeeController {
	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

//	@ApiOperation(value = "Insert multiple employees", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	@PostMapping(value = "/bulkEmployeeCreate")
//	public ResponseEntity<List<Employee>> bulkInsert(@RequestBody List<EmployeeRequest> requestEmployees) {
//		List<Employee> employees = employeeService.insertBulkEmployees(requestEmployees);
//		return new ResponseEntity<List<Employee>>(employees, HttpStatus.CREATED);
//
//	}

	@ApiOperation(value = "Insert one employee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(value = "/employee")
	public ResponseEntity<Employee> insertEmployee(@Valid @RequestBody EmployeeRequest requestEmployee) {
		Employee employee = employeeService.insertEmployee(requestEmployee);
		return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);

	}
	@ApiOperation(value = "Update Employee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PatchMapping(value = "/employee")
	public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDepartmentChange requestEmployee) {
		Object employee = employeeService.updateEmployeeDepartment(requestEmployee);
		return new ResponseEntity<>(employee, HttpStatus.OK);

	}

	@ApiOperation(value = "Delete Employee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@DeleteMapping(value = "/employee")
	public ResponseEntity<Long> deleteEmployee(@RequestParam(name = "id", required = true) Long id)
			throws Exception {
			Long deletedEmployee = employeeService.deleteEmployee(id);
			return new ResponseEntity<Long>(deletedEmployee, HttpStatus.OK);
		} 
		
	@ApiOperation(value = "Find Employee by Jmbg", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(value = "/employee")
	public ResponseEntity<Employee> getEmployee(@RequestParam(name = "jmbg", required = true) String jmbg)
			throws Exception {
	      Employee employee = employeeService.getEmployeeByJmbg(jmbg);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@ApiOperation(value = "Get All Employees", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(value = "/allemployees")
	public ResponseEntity<List<Employee>> getEmployees() throws Exception {
		List<Employee> employees = employeeService.getAllEmployees();
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}

	@ApiOperation(value="Change Employee data based on id",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	@PutMapping(value="/employee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long id,  @RequestBody EmployeeRequest employeeRequest){
		Employee employee = employeeService.updateEmployee(employeeRequest, id);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

}
package com.outofoffice.outofoffice.rest;

import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.outofoffice.outofoffice.configuration.RabbitConfiguration;
import com.outofoffice.outofoffice.model.Employee;
import com.outofoffice.outofoffice.requestobjects.EmployeeDepartmentChange;
import com.outofoffice.outofoffice.requestobjects.EmployeeRequest;
import com.outofoffice.outofoffice.requestobjects.NewUser;
import com.outofoffice.outofoffice.responseobjects.HolidayResponse;
import com.outofoffice.outofoffice.responseobjects.LeaveRequestResponse;
import com.outofoffice.outofoffice.responseobjects.LeaveRequestResponse2;
import com.outofoffice.outofoffice.responseobjects.NotificationResponse;
import com.outofoffice.outofoffice.service.EmployeeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/")
public class EmployeeController {
	private final EmployeeService employeeService;
	
	@Autowired
	RestTemplate restTemplate;  
	@Autowired
	RabbitTemplate rabbitTemplate;
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@ApiOperation(value = "Insert one employee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(value = "/employee")
	public ResponseEntity<Employee> insertEmployee(@Valid @RequestBody EmployeeRequest requestEmployee) {
		Employee employee = employeeService.insertEmployee(requestEmployee);
		
		NewUser user = new NewUser(requestEmployee.getUsername(), requestEmployee.getEmail(), requestEmployee.getPassword());
		rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE, RabbitConfiguration.ROUTING_KEY4, user);
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

	@ApiOperation(value = "Find Employee by Username", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(value = "/employee/username")
	public ResponseEntity<Employee> getEmployeeUsername(@RequestParam(name = "username", required = true) String username)
			throws Exception {
	      Employee employee = employeeService.getEmployeeByUsername(username);
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
    
	@ApiOperation(value="Check if employee can take a vacation based on his id and num days",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	@GetMapping(value="/employee/{id}/{days}")
	public ResponseEntity<LeaveRequestResponse> checkForRequest(@PathVariable(value="id") Long id, @PathVariable(value="days") Long days){
		LeaveRequestResponse response = employeeService.getEmployeesFromSameDepartment(id, days);
		return new ResponseEntity<LeaveRequestResponse>(response, HttpStatus.OK);
	}
	@ApiOperation(value="Send employees to holiday service",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value="/getAllEmployeesNames")
	public ResponseEntity<List<HolidayResponse>> getAllNames(){
		List<HolidayResponse> response = employeeService.getAllEmployeesNames();
		return new ResponseEntity<List<HolidayResponse>>(response, HttpStatus.OK);
	}
	
	@ApiOperation(value="Send employees to holiday service",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	@GetMapping(value="/getAllEmployeesNamesMap")
	public ResponseEntity<Map<Long, String>> getAllNamesMap(){
		Map<Long, String> response = employeeService.getAllEmployeesNamesMap();
		return new ResponseEntity<Map<Long, String>>(response, HttpStatus.OK);
	}
	
	@ApiOperation(value="Send employees to notification service")
	@PostMapping(value="/getAllEmployeesByIds")
	public ResponseEntity<List<NotificationResponse>> getAllEmployeesByIds(@RequestBody List<Long> ids){
		System.out.println(ids);
		List<NotificationResponse> response = employeeService.getAllEmployeesByIds(ids);
		return new ResponseEntity<List<NotificationResponse>>(response, HttpStatus.OK);
	}
	
	@GetMapping(value="/getEmployeesNames")
	public ResponseEntity<List<LeaveRequestResponse2>> getNamesAndDepartments(){
		List<LeaveRequestResponse2> response = employeeService.getEmployeesNamesAndDepartments();
		return new ResponseEntity<List<LeaveRequestResponse2>>(response, HttpStatus.OK);
	}
	
	@GetMapping(value="/employeeName/{id}")
	public ResponseEntity<String> getName(@PathVariable(value = "id") Long id){
		Employee employee = employeeService.getEmployeeById(id);
		String response = employee.getFirstnameLastName();
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
}

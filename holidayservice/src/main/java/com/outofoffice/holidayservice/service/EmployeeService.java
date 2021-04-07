package com.outofoffice.holidayservice.service;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.outofoffice.holidayservice.errorhandling.NotFoundException;
import com.outofoffice.holidayservice.model.Employee;
import com.outofoffice.holidayservice.repository.EmployeeRepository;
import com.outofoffice.holidayservice.requestobjects.EmployeeRequest;

@Service
public class EmployeeService {
	private final EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository =  employeeRepository;	
	}
	
	public ResponseEntity<?> insertEmployee(EmployeeRequest emp) {
		Employee employee = new Employee(emp.getId(), emp.getFirstnameLastName());
		Employee newemp = employeeRepository.save(employee);
		
		return new ResponseEntity<>(newemp, HttpStatus.OK);
	}
	
	public ResponseEntity<?> updateEmployee(EmployeeRequest employee, long id){
		String id_string = id + "";
		Employee updatedEmployee =  employeeRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(id_string, "Employee", "ID", ""));
			
		updatedEmployee.setFirstnameLastName(employee.getFirstnameLastName());
	
		Employee upholy = employeeRepository.save(updatedEmployee);
		return new ResponseEntity<>(upholy, HttpStatus.OK);
	}
	
	public ResponseEntity<?> getEmployeeById(long employeeID) {
		String id_string = employeeID + "";
        Employee employeeFound =  employeeRepository.findById(employeeID)
        		.orElseThrow(() -> new NotFoundException(id_string, "Employee", "ID", ""));
			
        return new ResponseEntity<>(employeeFound, HttpStatus.OK);
    }
	
	public ResponseEntity<?> deleteEmployee(Long id){
		String id_string = id + "";
		Employee employeeForDelete =  employeeRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(id_string, "Employee", "ID", ""));
			
		employeeRepository.delete(employeeForDelete);
		Long deletedEmployee = employeeForDelete.getId();
			
		return new ResponseEntity<Long>(deletedEmployee, HttpStatus.OK);
	}

}

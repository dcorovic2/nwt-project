package com.outofoffice.outofoffice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.util.RequestPayload;
import com.outofoffice.outofoffice.model.Employee;
import com.outofoffice.outofoffice.repository.EmployeeRepository;

@Service
public class EmployeeService {
	private final EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public List<Employee> insertBulkEmployees(List<Employee> employees) {
		List<Employee> insertedEmployees = new ArrayList<Employee>();
		employees.forEach(employee ->  {
			insertedEmployees.add(insertEmployee(employee));
		});
		
		return insertedEmployees;
	}
	
	public Employee insertEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Employee updateEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public Long deleteEmployee(Long id) throws Exception {
		final Optional<Employee> employeeForDelete =  employeeRepository.findById(id);
		if (!employeeForDelete.isPresent()) {
			throw new Exception("Employee with given id does not exist");
		}
		employeeRepository.delete(employeeForDelete.get());
		return employeeForDelete.get().getId();
	}
	
	public List<Employee> getEmployeesByName(String jmbg) {
		return employeeRepository.getAllEmployeesWithGivenName(jmbg);
	}
}

package com.outofoffice.notificationsservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.outofoffice.notificationsservice.errorhandling.NoDataException;
import com.outofoffice.notificationsservice.errorhandling.NotFoundException;
import com.outofoffice.notificationsservice.model.Employee;
import com.outofoffice.notificationsservice.repository.EmployeeRepository;
import com.outofoffice.notificationsservice.requestobjects.EmployeeRequest;


@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	public ResponseEntity<?> insertEmployee(EmployeeRequest employee) {
		Employee empl = new Employee(employee.getAllowance(), employee.getDepartmentId(), employee.getEmail(),
				employee.getFirstnameLastName());
		Employee newepl = employeeRepository.save(empl);
		return new ResponseEntity<>(newepl, HttpStatus.OK);

	}

	public ResponseEntity<?> getEmployeeList() {
		List<Employee> employeeList = employeeRepository.findAll();
		if (employeeList == null) {
			throw new NoDataException();
		}
		return new ResponseEntity<>(employeeList, HttpStatus.OK);
	}

	public ResponseEntity<?> getEmployeeById(long employeeID) {
		String id_string = employeeID + " ";
		Employee empl = employeeRepository.findById(employeeID)
				.orElseThrow(() -> new NotFoundException(id_string, "Employee", "ID", ""));
		return new ResponseEntity<>(empl, HttpStatus.OK);
	}

	public Employee GetEmployeeById(long employeeID) {
		String id_string = employeeID + "";
		Employee empl = employeeRepository.findById(employeeID)
				.orElseThrow(() -> new NotFoundException(id_string, "Employee", "ID", ""));
		return empl;
	}

	public ResponseEntity<?> updateEmployee(EmployeeRequest employee, long id) {
		String id_string = id + " ";
		Employee updateEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(id_string, "Employee", "ID", ""));
		updateEmployee.setAllowance(employee.getAllowance());
		updateEmployee.setDepartmentId(employee.getDepartmentId());
		updateEmployee.setEmail(employee.getEmail());
		updateEmployee.setFirstnameLastName(employee.getFirstnameLastName());
		Employee upemployee = employeeRepository.save(updateEmployee);
		return new ResponseEntity<>(upemployee, HttpStatus.OK);
	}

	public ResponseEntity<?> deleteEmployee(Long id) {
		final Optional<Employee> employeeForDelete = employeeRepository.findById(id);
		if (!employeeForDelete.isPresent()) {
			throw new NoDataException();
		}
		employeeRepository.delete(employeeForDelete.get());
		Long deleteEmpl = employeeForDelete.get().getId();
		return new ResponseEntity<Long>(deleteEmpl, HttpStatus.OK);

	}

}

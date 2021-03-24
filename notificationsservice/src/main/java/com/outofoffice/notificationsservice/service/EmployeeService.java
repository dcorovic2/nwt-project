package com.outofoffice.notificationsservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.outofoffice.notificationsservice.error.ErrorMessage;
import com.outofoffice.notificationsservice.model.Employee;
import com.outofoffice.notificationsservice.model.Notification;
import com.outofoffice.notificationsservice.model.NotificationsType;
import com.outofoffice.notificationsservice.repository.EmployeeRepository;
import com.outofoffice.notificationsservice.requestobjects.EmployeeRequest;
import com.outofoffice.notificationsservice.requestobjects.NotificationRequest;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final ErrorMessage errorMessage =  new ErrorMessage("Something went wrong!");
	
	public EmployeeService(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}
	
	public ResponseEntity<?> insertEmployee(EmployeeRequest employee) {
		try {
			Employee empl= new Employee(employee.getAllowance(),employee.getDepartmentId(),employee.getEmail(),employee.getFirstnameLastName());	
			Employee newepl = employeeRepository.save(empl);
			return new ResponseEntity<>(newepl, HttpStatus.OK);
		}catch (Exception e) {
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	public ResponseEntity<?> getEmployeeList() {
        try {
            List<Employee> employeeList = employeeRepository.findAll();
            if(employeeList == null) {
            	 return new ResponseEntity<>( new ErrorMessage("There is no employees in the DB"),HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(employeeList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	public ResponseEntity<?> getEmployeeById(long employeeID) {
        try {
        	Employee empl = employeeRepository.findById(employeeID).get();
            if(empl == null)
                return new ResponseEntity<>(new ErrorMessage("Employee with given id does not exist"),HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(empl, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	

	public Employee GetEmployeeById(long employeeID) {
	try {
       return employeeRepository.findById(employeeID).get();
	} catch (Exception e) {
  	  return null;
  }
}
	
	
	public ResponseEntity<?> updateEmployee(EmployeeRequest employee, long id){
	    try {
	    	Employee updateEmployee = employeeRepository.findById(id).get();
	    	updateEmployee.setAllowance(employee.getAllowance());
	    	updateEmployee.setDepartmentId(employee.getDepartmentId());
	    	updateEmployee.setEmail(employee.getEmail());
	    	updateEmployee.setFirstnameLastName(employee.getFirstnameLastName());
	    	Employee upemployee = employeeRepository.save(updateEmployee);
	    	
	    	return new ResponseEntity<>(upemployee, HttpStatus.OK);
	    } catch (Exception e) {
	    	  return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	public ResponseEntity<?> deleteEmployee(Long id) {
		try {
		final Optional<Employee> employeeForDelete =  employeeRepository.findById(id);
		if (!employeeForDelete.isPresent()) {
			throw new Exception("Employee with given id does not exist");
		}
		employeeRepository.delete(employeeForDelete.get());
		Long deleteEmpl = employeeForDelete.get().getId();	
		return new ResponseEntity<Long>(deleteEmpl, HttpStatus.OK);
		
		} catch (Exception e) {
	    	  return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
      }
	}

}

package com.outofoffice.outofoffice.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.outofoffice.outofoffice.errorhandling.NoDataException;
import com.outofoffice.outofoffice.errorhandling.NotSucesfullException;
import com.outofoffice.outofoffice.errorhandling.NotFoundException;
import com.outofoffice.outofoffice.model.Auth;
import com.outofoffice.outofoffice.model.Employee;
import com.outofoffice.outofoffice.repository.AuthRepository;
import com.outofoffice.outofoffice.repository.EmployeeRepository;
import com.outofoffice.outofoffice.requestobjects.LoginRequest;

@Service
public class LoginService {
	private final AuthRepository authRepository;
	private final EmployeeService employeeService;
	private final EmployeeRepository employeeRepository;

	public LoginService(AuthRepository authRepository, EmployeeService employeeService, EmployeeRepository employeeRepository) {
		super();
		this.authRepository = authRepository;
		this.employeeService = employeeService;
		this.employeeRepository = employeeRepository;
	}
	public Auth insertLogin(LoginRequest loginRequest) {
		Employee employee = employeeService.getEmployeeById(loginRequest.getEmployee_id());
		try {
			Auth auth = new Auth();
			auth.setPassword(loginRequest.getPassword());
			auth.setUsername(loginRequest.getUsername());
			auth.setEmployee(employee);
			return authRepository.save(auth);
		} catch (Exception e) {
			throw new NotSucesfullException();
		}

	}

	public List<Auth> getAll() {
		List<Auth> all_auth = new ArrayList<Auth>();
		all_auth = authRepository.findAll();
		if(all_auth.isEmpty()) throw new NoDataException();
		try {
			return all_auth; 
		} catch (Exception e) {
			throw new NotSucesfullException();
		}

	}
	
	public Long deleteAuth(Long employee_id) {
		String id_string = employee_id+"";
		Auth authForDelete = authRepository.findByEmployeeId(employee_id);
		if(authForDelete==null) throw new NotFoundException("", "Auth data", "employee_id", id_string);
		try {
			authRepository.delete(authForDelete);
			return authForDelete.getId();
		} catch (Exception e) {
			throw new NotSucesfullException();
		}	
	}
	
	public Auth updateAuth(LoginRequest loginRequest, Long id) {
		Auth updated_auth= authRepository.findById(id).orElseThrow(()-> new NotFoundException("", "Auth data", "id",""));
		updated_auth.setPassword(loginRequest.getPassword());
		updated_auth.setUsername(loginRequest.getUsername());
		String id_string = loginRequest.getEmployee_id()+"";
		Employee employee = employeeRepository.findById(loginRequest.getEmployee_id()).orElseThrow(()-> new NotFoundException(id_string, "Auth data", "employee id",""));
		updated_auth.setEmployee(employee);
		return authRepository.save(updated_auth);
	}
	
}

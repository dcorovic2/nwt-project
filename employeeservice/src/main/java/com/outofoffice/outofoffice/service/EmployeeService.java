package com.outofoffice.outofoffice.service;

import com.outofoffice.outofoffice.configuration.RabbitConfiguration;
import com.outofoffice.outofoffice.errorhandling.CustomRestExceptionHandler;
import com.outofoffice.outofoffice.errorhandling.NoDataException;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.outofoffice.outofoffice.errorhandling.NotFoundException;
import com.outofoffice.outofoffice.errorhandling.NotSucesfullException;
import com.outofoffice.outofoffice.errorhandling.NotValidParamException;
import com.outofoffice.outofoffice.model.Auth;
import com.outofoffice.outofoffice.model.Department;
import com.outofoffice.outofoffice.model.Employee;
import com.outofoffice.outofoffice.model.Role;
import com.outofoffice.outofoffice.repository.AuthRepository;
import com.outofoffice.outofoffice.repository.DepartmentRepository;
import com.outofoffice.outofoffice.repository.EmployeeRepository;
import com.outofoffice.outofoffice.repository.RoleRepository;
import com.outofoffice.outofoffice.requestobjects.AddedEmployee;
import com.outofoffice.outofoffice.requestobjects.EmployeeDepartmentChange;
import com.outofoffice.outofoffice.requestobjects.EmployeeRequest;
import com.outofoffice.outofoffice.requestobjects.LoginRequest;
import com.outofoffice.outofoffice.responseobjects.HolidayResponse;
import com.outofoffice.outofoffice.responseobjects.LeaveRequestResponse;
import com.outofoffice.outofoffice.responseobjects.LeaveRequestResponse2;
import com.outofoffice.outofoffice.responseobjects.NotificationResponse;

import io.grpc.stub.StreamObserver;

@Service
public class EmployeeService {
	private final EmployeeRepository employeeRepository;
	private final DepartmentService departmentService;
	private final RoleService roleService;
	private final AuthRepository authRepository;
	private final DepartmentRepository departmentRepository;
	
	
	
	public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository,
			RoleRepository roleRepository, AuthRepository authRepository) {
		this.employeeRepository = employeeRepository;
		this.departmentService = new DepartmentService(departmentRepository);
		this.roleService = new RoleService(roleRepository);
		this.authRepository = authRepository;
		this.departmentRepository = departmentRepository;
	}

	public List<Employee> insertBulkEmployees(List<EmployeeRequest> employees) {
		// try {
		List<Employee> insertedEmployees = new ArrayList<Employee>();
		employees.forEach(employee -> {
			insertedEmployees.add(insertEmployee(employee));
		});

		return insertedEmployees;
		// } catch (Exception e) {
//			throw new NotSucesfullException();
//		}
	}

	public Employee insertEmployee(EmployeeRequest employeeReq) {
		Department department = departmentService.getById(employeeReq.getDepartmentId());
		Role role = roleService.getById(employeeReq.getRoleId());
		Employee employee = new Employee();
		employee.setDepartment(department);
		employee.setRole(role);
		employee.setAllowance(employeeReq.getAllowance());
		employee.setEmail(employeeReq.getEmail());
		employee.setFirstnameLastName(employeeReq.getFirstnameLastName());
		employee.setHireDate(employeeReq.getHireDate());
		employee.setJmbg(employeeReq.getJmbg());
		employee.setJobRole(employeeReq.getJobRole());
		employee.setPhoneNumber(employeeReq.getPhoneNumber());
		employee.setRemainingDays(employeeReq.getRemainingDays());
		
		
		return employeeRepository.save(employee);
	}

	public Employee updateEmployeeDepartment(EmployeeDepartmentChange employee) {
		Employee updated_employee = this.getEmployeeByJmbg(employee.getEmployee_jmbg());
		Department new_department = this.departmentService.getByCode(employee.getDepartment_code());
		updated_employee.setDepartment(new_department);
		return employeeRepository.save(updated_employee);
	}

	public Employee updateEmployee(EmployeeRequest employee, Long id) {
		String id_string = id + "";
		Employee updated_employee = this.employeeRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(id_string, "Employee", "id", ""));
		try {
			updated_employee.setAllowance(employee.getAllowance());
			updated_employee.setEmail(employee.getEmail());
			updated_employee.setFirstnameLastName(employee.getFirstnameLastName());
			updated_employee.setHireDate(employee.getHireDate());
			updated_employee.setJmbg(employee.getJmbg());
			updated_employee.setJobRole(employee.getJobRole());
			updated_employee.setPhoneNumber(employee.getPhoneNumber());
			updated_employee.setRemainingDays(employee.getRemainingDays());
			Department new_department = this.departmentRepository.findById(employee.getDepartmentId()).get();
			updated_employee.setDepartment(new_department);
			return employeeRepository.save(updated_employee);
		} catch (Exception e) {
			throw new NotSucesfullException();
		}
	}

	public Long deleteEmployee(Long id) {
		String id_string = id + "";
		Auth authForDelete = authRepository.findByEmployeeId(id);
		authRepository.delete(authForDelete);
		Employee employeeForDelete = this.employeeRepository.findById(id).get();
		employeeRepository.delete(employeeForDelete);
		return employeeForDelete.getId();
	}

	public Employee getEmployeeByJmbg(String jmbg) {
		try {
			return employeeRepository.getEmployeeByJmbg(jmbg);
		} catch (Exception e) {
			throw new NotFoundException("", "Employee", "jmbg", jmbg);
		}

	}
	
	public Employee getEmployeeByUsername(String username) {
		try {
			return employeeRepository.getEmployeeByUsername(username);
		} catch (Exception e) {
			throw new NotFoundException("", "Employee", "username", username);
		}

	}

	public List<Employee> getAllEmployees() {
		List<Employee> return_employees = new ArrayList<Employee>();
		return_employees = employeeRepository.findAll();
		if (return_employees.isEmpty())
			throw new NoDataException();
		else
			return return_employees;
	}

	public Employee getEmployeeById(Long id) {
		String id_string = id + "";
		return employeeRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(id_string, "Employee", "id", ""));
	}

	public LeaveRequestResponse getEmployeesFromSameDepartment(Long id, Long days) {
		LeaveRequestResponse response = new LeaveRequestResponse();
		response.setEmployee_ids(employeeRepository.findByDepartmentId(id));
		if (days > employeeRepository.findAllowanceByEmployeeId(id))
			response.setAllowance(false);
		else
			response.setAllowance(true);
		response.setDepartmentallowance(
				departmentRepository.findAllowance(employeeRepository.findDepartmentIdByEmployeeId(id)));
		response.setDays(employeeRepository.findAllowanceByEmployeeId(id));
		return response;
	}

	public List<HolidayResponse> getAllEmployeesNames() {
		List<HolidayResponse> names = new ArrayList<HolidayResponse>();
		names = employeeRepository.findNamesAndIds();
		return names;
	}
	
	public List<LeaveRequestResponse2> getEmployeesNamesAndDepartments() {
		List<LeaveRequestResponse2> names = new ArrayList<LeaveRequestResponse2>();
		names = employeeRepository.findNamesAndDepartments();
		return names;
	}

	public Map<Long, String> getAllEmployeesNamesMap() {
		Map<Long, String> names = new HashMap<>();
		names = employeeRepository.findAll().stream()
				.collect(Collectors.toMap(Employee::getId, Employee::getFirstnameLastName));
		return names;
	}

	public List<NotificationResponse> getAllEmployeesByIds(List<Long> ids) {
		List<NotificationResponse> response = new ArrayList<NotificationResponse>();
		List<Employee> employees = employeeRepository.findAllById(ids);
		employees.forEach(employee -> {
			response.add(new NotificationResponse(employee.getId(), employee.getAllowance(),
					employee.getDepartment().getId(), employee.getEmail(), employee.getFirstnameLastName()));
		});
		return response;
	}
}

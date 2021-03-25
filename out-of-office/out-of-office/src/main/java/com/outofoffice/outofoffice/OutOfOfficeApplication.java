package com.outofoffice.outofoffice;

import org.hibernate.type.OffsetDateTimeType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.outofoffice.outofoffice.model.Auth;
import com.outofoffice.outofoffice.model.Department;
import com.outofoffice.outofoffice.model.Role;
import com.outofoffice.outofoffice.repository.DepartmentRepository;
import com.outofoffice.outofoffice.repository.RoleRepository;
import com.outofoffice.outofoffice.requestobjects.EmployeeRequest;
import com.outofoffice.outofoffice.requestobjects.LoginRequest;
import com.outofoffice.outofoffice.service.EmployeeService;
import com.outofoffice.outofoffice.service.LoginService;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class OutOfOfficeApplication {

	public static void main(String[] args) {
		SpringApplication.run(OutOfOfficeApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner departmentInitial(DepartmentRepository departmentReposiotry) {
//		return (args -> {
//			departmentReposiotry.save(new Department("SALES", "Sales", 5, "sales"));
//			departmentReposiotry.save(new Department("MARKETING", "Marketing", 6, "marketing"));
//			departmentReposiotry.save(new Department("HR", "Human Resources", 5, "human resources"));
//			departmentReposiotry.save(new Department("PRODUCTION", "Production", 3, "production"));
//			departmentReposiotry.save(new Department("ACCOUNTING", "Accounting", 5, "accounting"));
//		});
//	}
//
//	@Bean
//	public CommandLineRunner RoleInitial(RoleRepository roleRepository) {
//		return (args -> {
//			roleRepository.save(new Role("ADMIN", "Admin", "admin"));
//			roleRepository.save(new Role("EMPLOYEE", "Employee", "employee"));
//		});
//	}
//
//	@Bean
//	public CommandLineRunner EmployeeInitial(EmployeeService employeeService) {
//		List<EmployeeRequest> employeeRequests = new ArrayList<EmployeeRequest>();
//		employeeRequests.add(new EmployeeRequest("21", "email", "ime prezime", OffsetDateTime.now(), "jmbg1", "jobRole1",
//				"phoneNumber1", 21, 1L, 2L));
//		employeeRequests.add(new EmployeeRequest("21", "email2", "ime prezime2", OffsetDateTime.now(), "jmbg2", "jobRole2",
//				"phoneNumber2", 21, 2L, 1L));
//		employeeRequests.add(new EmployeeRequest("21", "email3", "Esma Herenda Boja", OffsetDateTime.now(), "jmbg3", "jobRole3",
//				"phoneNumber3", 21, 3L, 2L));
//		employeeRequests.add(new EmployeeRequest("21", "email4", "Nudzejma Pozder", OffsetDateTime.now(), "jmbg4", "jobRole4",
//				"phoneNumber4", 21, 4L, 2L));
//		employeeRequests.add(new EmployeeRequest("21", "email5", "Belmin Divjan", OffsetDateTime.now(), "jmbg5", "jobRole5",
//				"phoneNumber5", 21, 5L, 2L));
//		employeeRequests.add(new EmployeeRequest("21", "email6", "Dalila Ćorović", OffsetDateTime.now(), "jmbg6", "jobRole6",
//				"phoneNumber6", 21, 3L, 2L));
//		employeeRequests.add(new EmployeeRequest("21", "email7", "Amina Herenda", OffsetDateTime.now(), "jmbg7", "jobRole7",
//				"phoneNumber7", 21, 2L, 2L));
//		employeeRequests.add(new EmployeeRequest("21", "email8", "Aida Herenda", OffsetDateTime.now(), "jmbg8", "jobRole8",
//				"phoneNumber8", 21, 1L, 2L));
//		employeeRequests.add(new EmployeeRequest("21", "email9", "Samir Herenda", OffsetDateTime.now(), "jmbg9", "jobRole9",
//				"phoneNumber9", 21, 2L, 2L));
//		return (args -> {
//			employeeService.insertBulkEmployees(employeeRequests);
//		});
//
//	}
//
//	@Bean
//	public CommandLineRunner AuthInitial(LoginService loginService) {
//		List<LoginRequest> loginServiceRequests = new ArrayList<LoginRequest>();
//		loginServiceRequests.add((new LoginRequest("username1", "pass1", 1L)));
//		loginServiceRequests.add((new LoginRequest("username2", "pass2", 2L)));
//		loginServiceRequests.add((new LoginRequest("username3", "pass3", 3L)));
//		loginServiceRequests.add((new LoginRequest("username4", "pass4", 4L)));
//		loginServiceRequests.add((new LoginRequest("username5", "pass5", 5L)));
//		loginServiceRequests.add((new LoginRequest("username6", "pass6", 6L)));
//		loginServiceRequests.add((new LoginRequest("username7", "pass7", 7L)));
//		loginServiceRequests.add((new LoginRequest("username8", "pass8", 8L)));
//		loginServiceRequests.add((new LoginRequest("username9", "pass9", 9L)));
//		return (args -> {
//			loginServiceRequests.forEach(request -> loginService.insertLogin(request));
//		});
//	}
}

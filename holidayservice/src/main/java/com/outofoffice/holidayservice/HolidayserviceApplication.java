package com.outofoffice.holidayservice;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.outofoffice.holidayservice.errorhandling.RestTemplateResponseErrorHandler;
import com.outofoffice.holidayservice.model.Employee;
import com.outofoffice.holidayservice.model.Holiday;
import com.outofoffice.holidayservice.model.HolidayType;
import com.outofoffice.holidayservice.model.Notification;
import com.outofoffice.holidayservice.repository.EmployeeRepository;
import com.outofoffice.holidayservice.repository.HolidayRepository;
import com.outofoffice.holidayservice.repository.HolidayTypeRepository;
import com.outofoffice.holidayservice.repository.NotificationRepository;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@SpringBootApplication
@EnableDiscoveryClient
public class HolidayserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HolidayserviceApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
    RestTemplate restTemplateWithErrorHandler() {
        return new RestTemplateBuilder()
            .errorHandler(new RestTemplateResponseErrorHandler())
            .build();
    }
	
	@Bean
	public CommandLineRunner addingNotifications(HolidayRepository holidayRepository, HolidayTypeRepository holidayTypeRepository, NotificationRepository notificationRepository,
			EmployeeRepository employeeRepository){
		return (args -> {
			HolidayType holidayType = new HolidayType("For all", "New Year", "NY", "NY");
			holidayTypeRepository.save(holidayType);
			
			HolidayType holidayType1 = new HolidayType("Not for all", "Eid", "Eid", "Eid");
			holidayTypeRepository.save(holidayType1);
			
			Employee employee = new Employee(1L, "Dalila Corovic");
			Employee employee1 = new Employee(2L, "Nudzejma Pozder");
			Employee employee2 = new Employee(3L, "Belmin Divjan");
			Employee employee3 = new Employee(4L, "Esma Herenda");
			Employee employee4 = new Employee(5L, "Neko Nekic");
			employeeRepository.save(employee);
			employeeRepository.save(employee1);
			employeeRepository.save(employee2);
			employeeRepository.save(employee3);
			employeeRepository.save(employee4);
			
			List<Employee> listEmployees  = new ArrayList<>();
			listEmployees.add(employee);
			listEmployees.add(employee1);
			listEmployees.add(employee2);
			listEmployees.add(employee3);
			listEmployees.add(employee4);
			
			List<Employee> listEmployees1  = new ArrayList<>();
			listEmployees1.add(employee4);
			
			LocalDate start = LocalDate.parse("2020-12-31");
			LocalDate end = LocalDate.parse("2021-01-04");
			
			Holiday holiday = new Holiday(start, end, listEmployees, holidayType);
			holidayRepository.save(holiday);
			
			
			LocalDate start1 = LocalDate.of(2021, Month.MAY, 13);
			LocalDate end1 = LocalDate.of(2021, Month.MAY, 16);
			
			Holiday holiday1 = new Holiday(start1, end1, listEmployees1, holidayType1);
			holidayRepository.save(holiday1);
						
		});
	}

}

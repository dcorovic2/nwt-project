package com.outofoffice.holidayservice;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.outofoffice.holidayservice.model.Employee;
import com.outofoffice.holidayservice.model.Holiday;
import com.outofoffice.holidayservice.model.HolidayType;
import com.outofoffice.holidayservice.model.Notification;
import com.outofoffice.holidayservice.repository.EmployeeRepository;
import com.outofoffice.holidayservice.repository.HolidayRepository;
import com.outofoffice.holidayservice.repository.HolidayTypeRepository;
import com.outofoffice.holidayservice.repository.NotificationRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class HolidayserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HolidayserviceApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
	    return new RestTemplate();
	} 
	
	@Bean
	public CommandLineRunner addingNotifications(HolidayRepository holidayRepository, HolidayTypeRepository holidayTypeRepository, NotificationRepository notificationRepository,
			EmployeeRepository employeeRepository){
		return (args -> {
			HolidayType holidayType = new HolidayType("code1", "Godisnji odmor", "godisnji", "Godisnji odmor");
			holidayTypeRepository.save(holidayType);
			
			HolidayType holidayType1 = new HolidayType("code1", "Godisnji odmor", "godisnji", "Godisnji odmor");
			holidayTypeRepository.save(holidayType1);
			
			Employee employee = new Employee(1, "Dalila Corovic");
			employeeRepository.save(employee);
			
			List<Employee> listEmployees  = new ArrayList<>();
			listEmployees.add(employee);
			
			LocalDate start = LocalDate.of(2020, Month.OCTOBER, 8);
			LocalDate end = LocalDate.of(2020, Month.OCTOBER, 20);
			
			Holiday holiday = new Holiday(start, end, listEmployees, holidayType);
			holidayRepository.save(holiday);
			
			Notification notification = new Notification("neki tekst", listEmployees);
			notificationRepository.save(notification);
			
		});
	}

}

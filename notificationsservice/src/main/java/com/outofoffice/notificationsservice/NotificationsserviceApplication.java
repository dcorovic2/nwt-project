package com.outofoffice.notificationsservice;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.outofoffice.notificationsservice.model.Employee;
import com.outofoffice.notificationsservice.model.Holiday;
import com.outofoffice.notificationsservice.model.Notification;
import com.outofoffice.notificationsservice.model.NotificationsType;
import com.outofoffice.notificationsservice.repository.EmployeeRepository;
import com.outofoffice.notificationsservice.repository.HolidayRepository;
import com.outofoffice.notificationsservice.repository.NotificationsRepository;
import com.outofoffice.notificationsservice.repository.NotificationsTypeRepository;



@SpringBootApplication
public class NotificationsserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationsserviceApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner addingNotifications(NotificationsRepository notificationsRepository, NotificationsTypeRepository notificationsTypeRepository, EmployeeRepository employeeRepository, HolidayRepository holidayRepository){
		return (args -> {
			NotificationsType notificationtype1 = new NotificationsType("kod1","Nudza","displayNudza");
			notificationsTypeRepository.save(notificationtype1);
			NotificationsType notificationtype2 = new NotificationsType("kod2","Dalila","displayDalila");
			notificationsTypeRepository.save(notificationtype2);
			
			Employee employee1 = new Employee(20,5,"test@gmail.com","Neko Nekić");
			List<Employee> employees1 = new ArrayList<>();
			employees1.add(employee1);
			employeeRepository.save(employee1);
			
			Employee employee2 = new Employee(15,2,"bpozder@gmail.com","Benjamin Pozder");
			List<Employee> employees2 = new ArrayList<>();
			employees2.add(employee2);
			employeeRepository.save(employee2);
			
			Employee employee3 = new Employee(20,2,"npozder@gmail.com","Nudžejma Pozder");
			List<Employee> employees3 = new ArrayList<>();
			employees3.add(employee3);
			employeeRepository.save(employee3);
			
			Employee employee4 = new Employee(15,5,"dcorovic@gmail.com","Dalila Ćorović");
			List<Employee> employees4 = new ArrayList<>();
			employees4.add(employee4);
			employeeRepository.save(employee4);
			
			Employee employee5 = new Employee(15,2,"eherenda@gmail.com","Esma Herenda");
			List<Employee> employees5 = new ArrayList<>();
			employees5.add(employee5);
			employeeRepository.save(employee5);
			
			OffsetDateTime date = OffsetDateTime.now();
			Notification notification1 = new Notification(date,1,"test1",notificationtype2,employees1);
			notificationsRepository.save(notification1);
			Notification notification2 = new Notification(date,1,"test1",notificationtype1,employees4);
			notificationsRepository.save(notification2);
			
			Holiday holiday1 = new Holiday("Nova godina","Nova godina","državni praznik",employees2);
			Holiday holiday2 = new Holiday("Nova godina","Nova godina","državni praznik",employees3);
			Holiday holiday3 = new Holiday("Nova godina","Nova godina","državni praznik",employees4);
			holidayRepository.save(holiday1);
			holidayRepository.save(holiday2);
			holidayRepository.save(holiday3);
		});
	}
	
	
	
	

}




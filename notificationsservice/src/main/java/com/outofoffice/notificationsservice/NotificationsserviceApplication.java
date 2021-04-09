package com.outofoffice.notificationsservice;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.outofoffice.notificationsservice.model.Employee;
import com.outofoffice.notificationsservice.model.Holiday;
import com.outofoffice.notificationsservice.model.Notification;
import com.outofoffice.notificationsservice.model.NotificationsType;
import com.outofoffice.notificationsservice.repository.EmployeeRepository;
import com.outofoffice.notificationsservice.repository.HolidayRepository;
import com.outofoffice.notificationsservice.repository.NotificationsRepository;
import com.outofoffice.notificationsservice.repository.NotificationsTypeRepository;



@SpringBootApplication
@EnableDiscoveryClient
public class NotificationsserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationsserviceApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
	    return new RestTemplate();
	} 
	
	@Bean
	public CommandLineRunner addingNotifications(NotificationsRepository notificationsRepository, NotificationsTypeRepository notificationsTypeRepository, EmployeeRepository employeeRepository, HolidayRepository holidayRepository){
		return (args -> {
			NotificationsType notificationtype1 = new NotificationsType("A","LR is approved","display message");
			notificationsTypeRepository.save(notificationtype1);
			NotificationsType notificationtype2 = new NotificationsType("R","LR is rejected","display message");
			notificationsTypeRepository.save(notificationtype2);
			NotificationsType notificationtype3 = new NotificationsType("P","LR is pending","display message");
			notificationsTypeRepository.save(notificationtype3);
			NotificationsType notificationtype4 = new NotificationsType("R","LR is rejected","display message");
			notificationsTypeRepository.save(notificationtype4);
			
//			Employee employee1 = new Employee(20,5,"test@gmail.com","Neko Nekić");
//			List<Employee> employees1 = new ArrayList<>();
//			employees1.add(employee1);
//			employeeRepository.save(employee1);
//			
//			Employee employee2 = new Employee(2,15,2,"bpozder@gmail.com","Benjamin Pozder");
//			List<Employee> employees2 = new ArrayList<>();
//			employees2.add(employee2);
//			employeeRepository.save(employee2);
//			
//			Employee employee3 = new Employee(20,2,"npozder@gmail.com","Nudžejma Pozder");
//			List<Employee> employees3 = new ArrayList<>();
//			employees3.add(employee3);
//			employeeRepository.save(employee3);
//			
//			Employee employee4 = new Employee(2,15,5,"dcorovic@gmail.com","Dalila Ćorović");
//			List<Employee> employees4 = new ArrayList<>();
//			employees4.add(employee4);
//			employeeRepository.save(employee4);
//			
//			Employee employee5 = new Employee(1,15,2,"eherenda@gmail.com","Esma Herenda");
//			List<Employee> employees5 = new ArrayList<>();
//			employees5.add(employee5);
//			employeeRepository.save(employee5);
//			
//			OffsetDateTime date = OffsetDateTime.now();
//			OffsetDateTime date2 = OffsetDateTime.of(LocalDateTime.of(2017, 05, 12, 05, 45), ZoneOffset.ofHoursMinutes(6, 30));
//			Notification notification1 = new Notification(date,1,"Rejected leave request",notificationtype2,employees1,1);
//			notificationsRepository.save(notification1);
//			Notification notification2 = new Notification(date2,21,"Approved leave request",notificationtype1,employees4,0);
//			notificationsRepository.save(notification2);
//			Notification notification3 = new Notification(date,1,"New leave reqest created",notificationtype3,employees1,1);
//			notificationsRepository.save(notification3);
//			Notification notification4 = new Notification(date,21,"Rejected leave request",notificationtype1,employees4,0);
//			notificationsRepository.save(notification4);
//			Notification notification5 = new Notification(date,1,"Rejected leave request",notificationtype2,employees4,0);
//			notificationsRepository.save(notification5);
//			Notification notification6 = new Notification(date2,21,"Approved leave request",notificationtype1,employees4,0);
//			notificationsRepository.save(notification6);
//			Notification notification7 = new Notification(date,1,"New leave reqest created",notificationtype3,employees4,1);
//			notificationsRepository.save(notification7);
//			Notification notification8 = new Notification(date,21,"Rejected leave request",notificationtype1,employees4,0);
//			notificationsRepository.save(notification8);
//			Notification notification9 = new Notification(date,1,"New leave reqest created",notificationtype3,employees4,1);
//			notificationsRepository.save(notification9);
			
//			Holiday holiday1 = new Holiday("Nova godina","Nova godina","državni praznik",employees2);
//			Holiday holiday2 = new Holiday("Nova godina","Nova godina","državni praznik",employees3);
//			Holiday holiday3 = new Holiday("Nova godina","Nova godina","državni praznik",employees4);
//			holidayRepository.save(holiday1);
//			holidayRepository.save(holiday2);
//			holidayRepository.save(holiday3);
		});
	}
	
	
	
	

}




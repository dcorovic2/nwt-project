package com.outofoffice.leaverequestservice;

import java.sql.Date;
import java.time.OffsetDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.outofoffice.leaverequestservice.repository.LeaveRequestRepository;
import com.outofoffice.leaverequestservice.repository.LeaveTypeRepository;
import com.outofoffice.leaverequestservice.repository.LeaveStatusRepository;
import com.outofoffice.leaverequestservice.repository.NotificationsTypeRepository;

import com.outofoffice.leaverequestservice.model.LeaveRequest;
import com.outofoffice.leaverequestservice.model.LeaveStatus;
import com.outofoffice.leaverequestservice.model.LeaveType;
import com.outofoffice.leaverequestservice.model.NotificationsType;

@SpringBootApplication
@EnableDiscoveryClient
public class LeaveRequestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeaveRequestServiceApplication.class, args); 
	}
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
	    return new RestTemplate();
	} 
	@Bean
	public CommandLineRunner addingNotifications(LeaveRequestRepository leaveRequestRepository, LeaveTypeRepository leaveTypeRepository,
			LeaveStatusRepository leaveStatusRepository, NotificationsTypeRepository notificationsTypeRepository){
		return (args -> {
			LeaveType leavetype1 = new LeaveType("tip1","display1","name1");
			leaveTypeRepository.save(leavetype1);
			LeaveType leavetype2 = new LeaveType("tip2","display2","name2");
			leaveTypeRepository.save(leavetype2);
			LeaveType leavetype3 = new LeaveType("tip3","display3","name3");
			leaveTypeRepository.save(leavetype2);
			LeaveType leavetype4 = new LeaveType("tip4","display4","name4");
			leaveTypeRepository.save(leavetype2);
			
			LeaveStatus leavestatus1 = new LeaveStatus("P","Pending","Pending","New request");
			leaveStatusRepository.save(leavestatus1);
			LeaveStatus leavestatus2 = new LeaveStatus("A","Status: Approved","Approve","Approved Request");
			leaveStatusRepository.save(leavestatus2);
			LeaveStatus leavestatus3 = new LeaveStatus("R","Status: Rejected","Reject","Rejected Request");
			leaveStatusRepository.save(leavestatus3);
			
			NotificationsType notification1 = new NotificationsType("P","Pending","Pending");
			notificationsTypeRepository.save(notification1);
			NotificationsType notification2 = new NotificationsType("A","Approve","Approved Request");
			notificationsTypeRepository.save(notification2);
			NotificationsType notification3 = new NotificationsType("R","Reject","Rejected Request");
			notificationsTypeRepository.save(notification3);
			
			OffsetDateTime date1 = OffsetDateTime.now();
			
			
			LeaveRequest request1 = new LeaveRequest("comment1", 10, 1L, date1, date1.plusDays(10), leavetype1, leavestatus1, notification1);
			LeaveRequest request2 = new LeaveRequest("comment2", 20, 4L, date1, date1.plusDays(20), leavetype1, leavestatus1, notification1);
			LeaveRequest request3 = new LeaveRequest("comment3", 14, 6L, date1, date1.plusDays(14), leavetype1, leavestatus1, notification1);
			leaveRequestRepository.save(request1);
			leaveRequestRepository.save(request2);
			leaveRequestRepository.save(request3);
		});
	}

}

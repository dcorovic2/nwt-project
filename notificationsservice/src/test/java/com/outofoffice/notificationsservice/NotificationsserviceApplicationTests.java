package com.outofoffice.notificationsservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.outofoffice.notificationsservice.model.Notification;
import com.outofoffice.notificationsservice.repository.NotificationsRepository;
import com.outofoffice.notificationsservice.repository.NotificationsTypeRepository;
import com.outofoffice.notificationsservice.requestobjects.NotificationRequest;
import com.outofoffice.notificationsservice.requestobjects.NotificationTypeRequest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest

@AutoConfigureMockMvc
class NotificationsserviceApplicationTests {
	@Resource
	private  NotificationsRepository notificationRepository;
	@Resource
	private  NotificationsTypeRepository notificationTypeRepository;
	@Autowired
	private MockMvc mockMvc;
	private Logger logger = LoggerFactory.getLogger(NotificationsserviceApplicationTests.class);

	@Autowired
	private ObjectMapper objectMapper;
	private Validator validator;

	//DTO Bean Validation
	
	@Test
	void testGoodNotificationPOST() throws Exception {
		NotificationRequest notifikacija = new NotificationRequest();
		notifikacija.setDepartmentId(14);
		notifikacija.setText("example");
		notifikacija.setDismiss(1);
		this.mockMvc
				.perform(MockMvcRequestBuilders
				.post("/notification/{employeeId}/{notificationTypeId}", "1", "2")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(notifikacija)))
				.andExpect(status().isOk());
	}
	
	@Test
	void testBadNotificationPOST() throws Exception {
		NotificationRequest notifikacija = new NotificationRequest();
		notifikacija.setDepartmentId(143243);
		notifikacija.setText("example");
		notifikacija.setDismiss(0);
		this.mockMvc
				.perform(MockMvcRequestBuilders
				.post("/notification/{employeeId}/{notificationTypeId}", "1", "2")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(notifikacija)))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void testGoodNotificationGET() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders
				    .get("/notification/{notificationId}", "2")
			        .contentType("application/json"))
			        .andExpect(status().isOk());
	}
	
	@Test
	public void testBadNotificationGET() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders
				    .get("/notification/{notificationId}", "15")
			        .contentType("application/json"))
			        .andExpect(status().isNotFound());
	}
	

	@Test
	public void testGoodNotificationDELETE() throws Exception {	
		this.mockMvc.perform(MockMvcRequestBuilders
				    .delete("/notifications/{notificationsId}", "2")
			        .contentType("application/json"))
			        .andExpect(status().isOk());
	}
	
	@Test
	public void testBadNotificationDELETE() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders
				    .delete("/notifications/{notificationsId}", "52")
			        .contentType("application/json"))
			        .andExpect(status().isNotFound());
	}



	@BeforeEach
	void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	//Hibernate-Validator Unit tests
	@Test
	public void testBadNotificationsPOST() {
		NotificationRequest notifikacija = new NotificationRequest();
		notifikacija.setDepartmentId(87567);
		notifikacija.setText("example");
		notifikacija.setDismiss(1);
		Set<ConstraintViolation<NotificationRequest>> violations = validator.validate(notifikacija);
		assertFalse(violations.isEmpty());
	}

	@Test
	public void testGoodNotificationsPOST() {
		NotificationRequest notifikacija = new NotificationRequest();
		notifikacija.setDepartmentId(8);
		notifikacija.setText("example");
		notifikacija.setDismiss(1);
		Set<ConstraintViolation<NotificationRequest>> violations = validator.validate(notifikacija);
		assertTrue(violations.isEmpty());
	}

	@Test
	public void testBadNotificationTypePOST() {
		NotificationTypeRequest notifikacija = new NotificationTypeRequest();
		notifikacija.setCode("this leave request isn't approved");
		notifikacija.setDisplayName("cancel");
		notifikacija.setName("cancel");
		Set<ConstraintViolation<NotificationTypeRequest>> violations = validator.validate(notifikacija);
		assertFalse(violations.isEmpty());
	}

	@Test
	public void testGoodNotificationTypePOST() {
		NotificationTypeRequest notifikacija = new NotificationTypeRequest();
		notifikacija.setCode("A");
		notifikacija.setDisplayName("Leave request is approved");
		notifikacija.setName("LR is approved");
		Set<ConstraintViolation<NotificationTypeRequest>> violations = validator.validate(notifikacija);
		assertTrue(violations.isEmpty());
	}

	@Test
	public void testSavingNotification() {
		Notification notifikacija = new Notification();
		notifikacija.setDepartmentId(14);
		notifikacija.setText("example");
		notifikacija.setDismiss(1);
		
		Notification newnotif = notificationRepository.save(notifikacija);
		Notification notif = notificationRepository.findById(newnotif.getId()).get();
	        assertEquals("example", notif.getText());
		
	}
	
//	@Test
//	public void testSavingNotificationType() {
//		NotificationTypeRequest notifikacija = new NotificationTypeRequest();
//		notifikacija.setCode("A");
//		notifikacija.setDisplayName("Leave request is approved");
//		notifikacija.setName("LR is approved");
//		
//		NotificationsType  newnotif = notificationTypeRepository.save(notifikacija);
//		NotificationsType  notif = notificationTypeRepository.findById(newnotif.getId()).get();
//	        assertEquals("A", notif.getCode());
//		
//	}
}

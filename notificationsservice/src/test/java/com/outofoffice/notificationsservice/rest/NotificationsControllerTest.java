package com.outofoffice.notificationsservice.rest;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
import com.outofoffice.notificationsservice.requestobjects.NotificationRequest;

@SpringBootTest
@AutoConfigureMockMvc
class NotificationsControllerTest {
	@Resource
	private NotificationsRepository notificationRepository;
	
	@Autowired
	private MockMvc mockMvc;
	private Logger logger = LoggerFactory.getLogger(NotificationsControllerTest.class);

	@Autowired
	private ObjectMapper objectMapper;
	private Validator validator;

	// DTO Bean Validation
	// -------------------------------------------------------------
//	@Test
//	void testGoodNotificationPOST() throws Exception {
//		NotificationRequest notifikacija = new NotificationRequest();
//		notifikacija.setDepartmentId(14);
//		notifikacija.setText("example");
//		notifikacija.setDismiss(1);
//		this.mockMvc
//				.perform(MockMvcRequestBuilders.post("/notification/{employeeId}/{notificationTypeId}", "1", "2")
//						.contentType("application/json")
//						.content(objectMapper.writeValueAsString(notifikacija)))
//				.andExpect(status().isOk());
//	}
//
//	@Test
//	void testBadNotificationPOST() throws Exception {
//		NotificationRequest notifikacija = new NotificationRequest();
//		notifikacija.setDepartmentId(143243);
//		notifikacija.setText("example");
//		notifikacija.setDismiss(0);
//		this.mockMvc
//				.perform(MockMvcRequestBuilders.post("/notification/{employeeId}/{notificationTypeId}", "1", "2")
//						.contentType("application/json")
//						.content(objectMapper.writeValueAsString(notifikacija)))
//				.andExpect(status().isBadRequest());
//	}
//
//	// -------------------------------------------------------------
//	@Test
//	public void testGoodNotificationGETAllNotifications() throws Exception {
//		this.mockMvc.perform(MockMvcRequestBuilders.get("/all_notifications")
//				.contentType("application/json"))
//				.andExpect(status().isOk());
//	}
//
////	@Test
////	public void testBadNotificationGETAllNotifications() throws Exception {
////		this.mockMvc.perform(
////				MockMvcRequestBuilders.get("/all_notifications")
////				.contentType("application/json"))
////				.andExpect(status().isNotFound());
////	}
////	
//	// -------------------------------------------------------------
//	@Test
//	public void testGoodNotificationGETByNotifactionID() throws Exception {
//		this.mockMvc.perform(
//				MockMvcRequestBuilders.get("/notification/{notificationId}", "2")
//				.contentType("application/json"))
//				.andExpect(status().isOk());
//	}
//
//	@Test
//	public void testBadNotificationGETByNotifactionID() throws Exception {
//		this.mockMvc.perform(
//				MockMvcRequestBuilders.get("/notification/{notificationId}", "15")
//				.contentType("application/json"))
//				.andExpect(status().isNotFound());
//	}
//
//	// -------------------------------------------------------------
//	@Test
//	public void testGoodNotificationGETByEmployeeID() throws Exception {
//		this.mockMvc
//				.perform(MockMvcRequestBuilders.get("/notifications/{employeeId}", "4")
//						.contentType("application/json"))
//				.andExpect(status().isOk());
//	}
//
//	@Test
//	public void testBadNotificationGETByEmployeeID() throws Exception {
//		this.mockMvc.perform(
//				MockMvcRequestBuilders.get("/notifications/{employeeId}", "150")
//				.contentType("application/json"))
//				.andExpect(status().isNotFound());
//
//	}
//
//	// -------------------------------------------------------------
//	@Test
//	public void testGoodNotificationPATCHByNotificationID() throws Exception {
//		this.mockMvc.perform(
//				MockMvcRequestBuilders.patch("/notifications/{notificationId}", "4")
//				.param("dismiss", "1")
//		        .contentType("application/json"))
//		        .andExpect(status().isOk());
//	}
//
//	@Test
//	public void testBadNotificationPATCHByNotificationID() throws Exception {
//		this.mockMvc.perform(
//				MockMvcRequestBuilders.patch("/notifications/{notificationId}", "14")
//				.param("dismiss", "1")
//		        .contentType("application/json"))
//		        .andExpect(status().isNotFound());
//	}
//
//	// -------------------------------------------------------------
//	@Test
//	public void testGoodNotificationDELETE() throws Exception {
//		this.mockMvc.perform(
//				MockMvcRequestBuilders.delete("/notifications/{notificationsId}", "2")
//				.contentType("application/json"))
//				.andExpect(status().isOk());
//	}
//
//	@Test
//	public void testBadNotificationDELETE() throws Exception {
//		this.mockMvc.perform(
//				MockMvcRequestBuilders.delete("/notifications/{notificationsId}", "52")
//				.contentType("application/json"))
//				.andExpect(status().isNotFound());
//	}
//
//	// -------------------------------------------------------------
//	@BeforeEach
//	void setUp() {
//		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//		validator = factory.getValidator();
//	}
//
//	// Hibernate-Validator Unit tests
//
//	@Test
//	public void testBadNotificationsPOST() {
//		NotificationRequest notifikacija = new NotificationRequest();
//		notifikacija.setDepartmentId(87567);
//		notifikacija.setText("example");
//		notifikacija.setDismiss(1);
//		Set<ConstraintViolation<NotificationRequest>> violations = validator.validate(notifikacija);
//		assertFalse(violations.isEmpty());
//	}
//
//	@Test
//	public void testGoodNotificationsPOST() {
//		NotificationRequest notifikacija = new NotificationRequest();
//		notifikacija.setDepartmentId(8);
//		notifikacija.setText("example");
//		notifikacija.setDismiss(1);
//		Set<ConstraintViolation<NotificationRequest>> violations = validator.validate(notifikacija);
//		assertTrue(violations.isEmpty());
//	}
//
//	// -------------------------------------------------------------
//	@Test
//	public void testSavingNotification() {
//		Notification notifikacija = new Notification();
//		notifikacija.setDepartmentId(14);
//		notifikacija.setText("example");
//		notifikacija.setDismiss(1);
//
//		Notification newnotif = notificationRepository.save(notifikacija);
//		Notification notif = notificationRepository.findById(newnotif.getId()).get();
//		assertEquals("example", notif.getText());
//	}

}

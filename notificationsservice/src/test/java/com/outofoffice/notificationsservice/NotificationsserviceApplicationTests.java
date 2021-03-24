package com.outofoffice.notificationsservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.outofoffice.notificationsservice.requestobjects.NotificationRequest;
import com.outofoffice.notificationsservice.requestobjects.NotificationTypeRequest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
class NotificationsserviceApplicationTests {
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

		this.mockMvc
				.perform(MockMvcRequestBuilders
				.post("/notification/create/{employeeId}/{notificationTypeId}", "1", "2")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(notifikacija)))
				.andExpect(status().isOk());
	}
	
	@Test
	void testBadNotificationPOST() throws Exception {
		NotificationRequest notifikacija = new NotificationRequest();
		notifikacija.setDepartmentId(143243);
		notifikacija.setText("example");

		this.mockMvc
				.perform(MockMvcRequestBuilders
				.post("/notification/create/{employeeId}/{notificationTypeId}", "1", "2")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(notifikacija)))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void testGoodNotificationGET() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders
				    .get("/notification/{notificationId}", "1")
			        .contentType("application/json"))
			        .andExpect(status().isOk());
	}
	
	@Test
	public void testBadNotificationGET() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders
				    .get("/notification/{notificationId}", "15")
			        .contentType("application/json"))
			        .andExpect(status().isInternalServerError());
	}
	
	@Test
	public void testGoodNotificationPATCH() throws Exception {
		NotificationRequest notifikacija = new NotificationRequest();
		notifikacija.setDepartmentId(14);
		notifikacija.setText("example");
		this.mockMvc.perform(MockMvcRequestBuilders
				    .patch("/notifications/update/{notificationId}", "1")
				    .contentType("application/json")
					.content(objectMapper.writeValueAsString(notifikacija)))
					.andExpect(status().isOk());
	}
	
	@Test
	public void testBadNotificationPATCH() throws Exception {
		NotificationRequest notifikacija = new NotificationRequest();
		notifikacija.setDepartmentId(14);
		notifikacija.setText("example");
		this.mockMvc.perform(MockMvcRequestBuilders
				    .patch("/notifications/update/{notificationId}", "123")
				    .contentType("application/json")
					.content(objectMapper.writeValueAsString(notifikacija)))
					.andExpect(status().isInternalServerError());
	}

	@Test
	public void testGoodNotificationDELETE() throws Exception {	
		this.mockMvc.perform(MockMvcRequestBuilders
				    .delete("/notifications/delete/{notificationsId}", "2")
			        .contentType("application/json"))
			        .andExpect(status().isOk());
	}
	
	@Test
	public void testBadNotificationDELETE() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders
				    .delete("/notifications/delete/{notificationsId}", "52")
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
		Set<ConstraintViolation<NotificationRequest>> violations = validator.validate(notifikacija);
		assertFalse(violations.isEmpty());
	}

	@Test
	public void testGoodNotificationsPOST() {
		NotificationRequest notifikacija = new NotificationRequest();
		notifikacija.setDepartmentId(8);
		notifikacija.setText("example");
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
		notifikacija.setCode("Approved");
		notifikacija.setDisplayName("Leave request is approved");
		notifikacija.setName("LR is approved");
		Set<ConstraintViolation<NotificationTypeRequest>> violations = validator.validate(notifikacija);
		assertTrue(violations.isEmpty());
	}

}

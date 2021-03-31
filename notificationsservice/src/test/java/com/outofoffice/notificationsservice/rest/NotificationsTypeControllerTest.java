package com.outofoffice.notificationsservice.rest;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.outofoffice.notificationsservice.repository.NotificationsTypeRepository;
import com.outofoffice.notificationsservice.requestobjects.NotificationTypeRequest;


@SpringBootTest
@AutoConfigureMockMvc
class NotificationsTypeControllerTest {
	@Resource
	private  NotificationsTypeRepository notificationTypeRepository;
	
	@Autowired
	private MockMvc mockMvc;
	private Logger logger = LoggerFactory.getLogger(NotificationsTypeControllerTest.class);

	@Autowired
	private ObjectMapper objectMapper;
	private Validator validator;
	
	@Test
	void testGoodNotificationTypePOST() throws Exception {
		NotificationTypeRequest notificationType = new NotificationTypeRequest();
		notificationType.setCode("R");
		notificationType.setDisplayName("LR is rejected");
		notificationType.setName("LR is rejected");
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/notificationsType")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(notificationType)))
				.andExpect(status().isOk());
	}
	
	@Test
	void testBadNotificationTypePOST() throws Exception {
		NotificationTypeRequest notificationType = new NotificationTypeRequest();
		notificationType.setCode("test");
		notificationType.setDisplayName("LR is rejected");
		notificationType.setName("LR is rejected");
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/notificationsType")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(notificationType)))
				.andExpect(status().isBadRequest());
	}
	// -------------------------------------------------------------
	@Test
	public void testGoodNotificationGETAllNotifications() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/all_notification_types")
				.contentType("application/json"))
				.andExpect(status().isOk());
	}
	// -------------------------------------------------------------
	
	@Test
	public void testGoodNotificationTypePATCH() throws Exception {
		NotificationTypeRequest notificationType = new NotificationTypeRequest();
		notificationType.setCode("A");
		notificationType.setDisplayName("LR is approved");
		notificationType.setName("LR is approved");
		
		this.mockMvc.perform(MockMvcRequestBuilders
				    .patch("/notification_type/{notification_typeId}", "3")
			        .contentType("application/json")
			        .content(objectMapper.writeValueAsString(notificationType)))
			        .andExpect(status().isOk());
	}
	
	@Test
	public void testBad_RequestNotificationTypePATCH() throws Exception {
		NotificationTypeRequest notificationType = new NotificationTypeRequest();
		notificationType.setCode("test");
		notificationType.setDisplayName("LR is approved");
		notificationType.setName("LR is approved");
		
		this.mockMvc.perform(MockMvcRequestBuilders
				    .patch("/notification_type/{notification_typeId}", "3")
			        .contentType("application/json")
			        .content(objectMapper.writeValueAsString(notificationType)))
			        .andExpect(status().isBadRequest());
	}
	
	@Test
	public void testNot_FoundNotificationTypePATCH() throws Exception {
		NotificationTypeRequest notificationType = new NotificationTypeRequest();
		notificationType.setCode("A");
		notificationType.setDisplayName("LR is approved");
		notificationType.setName("LR is approved");
		
		this.mockMvc.perform(MockMvcRequestBuilders
				    .patch("/notification_type/{notification_typeId}", "32")
			        .contentType("application/json")
			        .content(objectMapper.writeValueAsString(notificationType)))
			        .andExpect(status().isNotFound());
	}
	
	// --------------------------------------------------------------------
	@Test
	public void testGoodNotificationTypeDELETE() throws Exception {
		this.mockMvc.perform(
				MockMvcRequestBuilders.delete("/notification_type/{notification_typeId}", "4")
				.contentType("application/json"))
				.andExpect(status().isOk());
	}

	@Test
	public void testBadNotificationTypeDELETE() throws Exception {
		this.mockMvc.perform(
				MockMvcRequestBuilders.delete("/notification_type/{notification_typeId}", "52")
				.contentType("application/json"))
				.andExpect(status().isNotFound());
	}
	// --------------------------------------------------------------------
	
	@BeforeEach
	void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	//Hibernate-Validator Unit tests
	@Test
	public void TestBadNotificationTypePOST() {
		NotificationTypeRequest notifikacija = new NotificationTypeRequest();
		notifikacija.setCode("this leave request isn't approved");
		notifikacija.setDisplayName("cancel");
		notifikacija.setName("cancel");
		Set<ConstraintViolation<NotificationTypeRequest>> violations = validator.validate(notifikacija);
		assertFalse(violations.isEmpty());
	}

	@Test
	public void TestGoodNotificationTypePOST() {
		NotificationTypeRequest notifikacija = new NotificationTypeRequest();
		notifikacija.setCode("A");
		notifikacija.setDisplayName("Leave request is approved");
		notifikacija.setName("LR is approved");
		Set<ConstraintViolation<NotificationTypeRequest>> violations = validator.validate(notifikacija);
		assertTrue(violations.isEmpty());
	}
	
}

package com.outofoffice.leaverequestservice.rest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
import com.outofoffice.leaverequestservice.requestobjects.LeaveRequestRequest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@AutoConfigureMockMvc
class LeaveRequestControllerTest {
	@Autowired
	private MockMvc mockMvc;
	private Logger logger = LoggerFactory.getLogger(LeaveRequestControllerTest.class);
	
	@Autowired
	private ObjectMapper objectMapper;
	private Validator validator;
	
	@BeforeEach
	void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testGoodInsert() {
		LeaveRequestRequest leaveRequest = new LeaveRequestRequest();
		leaveRequest.setComment("string");
		leaveRequest.setDaysNum(10);
		leaveRequest.setEmployeeId(2);
		leaveRequest.setStartDate(OffsetDateTime.now());
		leaveRequest.setTypeId(2);
		Set<ConstraintViolation<LeaveRequestRequest>> violations = validator.validate(leaveRequest);
		assertTrue(violations.isEmpty());
	}
	@Test
	public void testBadInsert() {
		LeaveRequestRequest leaveRequest = new LeaveRequestRequest();
		leaveRequest.setComment("st");
		leaveRequest.setDaysNum(0);
		leaveRequest.setEmployeeId(2);
		leaveRequest.setStartDate(OffsetDateTime.now());
		leaveRequest.setTypeId(2);
		Set<ConstraintViolation<LeaveRequestRequest>> violations = validator.validate(leaveRequest);
		assertFalse(violations.isEmpty());
	}
	@Test
	public void testWrongStartDate() {
		LeaveRequestRequest leaveRequest = new LeaveRequestRequest();
		leaveRequest.setComment("test");
		leaveRequest.setDaysNum(10);
		leaveRequest.setEmployeeId(2);
		leaveRequest.setStartDate(null);
		leaveRequest.setTypeId(1);
		Set<ConstraintViolation<LeaveRequestRequest>> violations = validator.validate(leaveRequest);
		assertFalse(violations.isEmpty());
	}
	@Test
	public void testBadDeleteRequest() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders
				    .delete("/request/{id}", "111")
			        .contentType("application/json"))
			        .andExpect(status().isNotFound());
	}
	@Test
	public void testGoodDeleteRequest() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders
				    .delete("/request/{id}", "1")
			        .contentType("application/json"))
			        .andExpect(status().isOk());
	}
	@Test
	public void testGoodGetRequest() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders
				    .get("/request/{id}", "2")
			        .contentType("application/json"))
			        .andExpect(status().isOk());
	}
	@Test
	public void testBadGetRequest() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders
				    .get("/request/{id}", "111")
			        .contentType("application/json"))
			        .andExpect(status().isNotFound());
	}

}


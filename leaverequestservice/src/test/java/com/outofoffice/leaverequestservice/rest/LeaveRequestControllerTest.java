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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.outofoffice.leaverequestservice.model.LeaveRequest;
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
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

	@Test
	public void testGoodInsertRequest() {
		LeaveRequestRequest leaveRequest = new LeaveRequestRequest();
		leaveRequest.setComment("string");
		leaveRequest.setDaysNum(10);
		leaveRequest.setEmployeeId(2L);
		leaveRequest.setStartDate(OffsetDateTime.now());
		leaveRequest.setTypeId(2);
		Set<ConstraintViolation<LeaveRequestRequest>> violations = validator.validate(leaveRequest);
		assertTrue(violations.isEmpty());
	}
	@Test
	public void testBadInsertRequest() {
		LeaveRequestRequest leaveRequest = new LeaveRequestRequest();
		leaveRequest.setComment("st");
		leaveRequest.setDaysNum(0);
		leaveRequest.setEmployeeId(2L);
		leaveRequest.setStartDate(OffsetDateTime.now());
		leaveRequest.setTypeId(2);
		Set<ConstraintViolation<LeaveRequestRequest>> violations = validator.validate(leaveRequest);
		assertFalse(violations.isEmpty());
	}
	@Test
	public void testBadDaysInsert() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/request")
				.content(asJsonString(new LeaveRequestRequest("comment", 0, 1L, null, 1)))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
	@Test
	public void testWrongStartDate() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/request")
				.content(asJsonString(new LeaveRequestRequest("comment", 10, 1L, null, 1)))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
	@Test
	public void testBadCommentInsert() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/request")
				.content(asJsonString(new LeaveRequestRequest("co", 10, 1L, OffsetDateTime.now(), 1)))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
	public void testGoodInsert() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/request")
				.content(asJsonString(new LeaveRequestRequest("comment", 10, 1L, OffsetDateTime.now(), 1)))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
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


package com.outofoffice.holidayservice.rest;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
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
import com.outofoffice.holidayservice.model.Employee;
import com.outofoffice.holidayservice.model.Notification;
import com.outofoffice.holidayservice.repository.EmployeeRepository;
import com.outofoffice.holidayservice.repository.HolidayRepository;
import com.outofoffice.holidayservice.repository.HolidayTypeRepository;

@SpringBootTest
@AutoConfigureMockMvc
class NotificationControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Resource
	private HolidayTypeRepository holidayTypeRepository;
	@Resource
	private HolidayRepository holidayRepository;
	@Resource
	private EmployeeRepository employeeRepository;


	@Test
	void postNotification() throws Exception {
		
		Employee employee = new Employee("Dalila Corovic");
		List<Employee> listEmployees = new ArrayList<>();
		listEmployees.add(employee);

		Notification notification = new Notification("neki tekst", listEmployees);

		this.mockMvc.perform(MockMvcRequestBuilders.post("/notification/1")
				    .contentType("application/json")
					.content(objectMapper.writeValueAsString(notification)))
				    .andExpect(status().isOk());
	}
	

}

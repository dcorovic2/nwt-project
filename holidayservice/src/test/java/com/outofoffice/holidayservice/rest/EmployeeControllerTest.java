package com.outofoffice.holidayservice.rest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.outofoffice.holidayservice.repository.EmployeeRepository;
import com.outofoffice.holidayservice.repository.HolidayRepository;
import com.outofoffice.holidayservice.repository.HolidayTypeRepository;
import com.outofoffice.holidayservice.requestobjects.EmployeeRequest;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {
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
	public void invalidNameError() throws Exception {
		
		EmployeeRequest employee = new EmployeeRequest();
		employee.setFirstnameLastName("D.C.");

		this.mockMvc.perform(MockMvcRequestBuilders.post("/employee")
				    .contentType("application/json")
				    .content(objectMapper.writeValueAsString(employee)))
				    .andExpect(status().isBadRequest());
	}
	
	@Test
	public void postEmployee() throws Exception {
		
		EmployeeRequest employee = new EmployeeRequest();
		employee.setFirstnameLastName("Dalila C.");

		this.mockMvc.perform(MockMvcRequestBuilders
					.post("/employee")
				    .contentType("application/json")
				    .content(objectMapper.writeValueAsString(employee)))
				    .andExpect(status().isOk());
	}
	
	@Test
	public void patchEmployee() throws Exception {
		
		EmployeeRequest employee = new EmployeeRequest("Dalila C.");

		this.mockMvc.perform(MockMvcRequestBuilders
					.patch("/employee/{id}", "1")
				    .contentType("application/json")
				    .content(objectMapper.writeValueAsString(employee)))
				    .andExpect(status().isOk());
	}
	
	@Test
	public void patchEmployeeFail() throws Exception {
		
		EmployeeRequest employee = new EmployeeRequest();
		employee.setFirstnameLastName("Dalila C.");

		this.mockMvc.perform(MockMvcRequestBuilders
					.patch("/employee/{id}", "25")
				    .contentType("application/json")
				    .content(objectMapper.writeValueAsString(employee)))
				    .andExpect(status().isNotFound());
	}
	
	@Test
	public void deleteEmployeeFail() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders
				    .delete("/employee/{id}", "11")
			        .contentType("application/json"))
			        .andExpect(status().isNotFound());
	}
	
	@Test
	public void getEmployee() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders
				    .get("/employee/getEmployee/{id}", "1")
			        .contentType("application/json"))
			        .andExpect(status().isOk());
	}
	
	@Test
	public void getEmployeeFail() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders
				    .get("/employee/getEmployee/{id}", "58")
			        .contentType("application/json"))
			        .andExpect(status().isNotFound());
	}

}

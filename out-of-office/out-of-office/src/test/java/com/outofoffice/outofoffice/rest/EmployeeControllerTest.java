package com.outofoffice.outofoffice.rest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.OffsetDateTime;

import javax.transaction.Transactional;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.outofoffice.outofoffice.OutOfOfficeApplication;
import com.outofoffice.outofoffice.requestobjects.EmployeeDepartmentChange;
import com.outofoffice.outofoffice.requestobjects.EmployeeRequest;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = OutOfOfficeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class EmployeeControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	private Validator validator;

	private void createTestEmployee() throws Exception {
		EmployeeRequest employee_req =new EmployeeRequest("21", "testemail", "testname", OffsetDateTime.now(),
				"9999999999999", "jobRole1",
			  	"phoneNumber1", 21, 1L, 2L);
		this.mockMvc
				.perform(MockMvcRequestBuilders
				.post("/employee")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(employee_req)))
				.andExpect(status().isCreated());
	}
	
	@Test
	void testEmployeesPOST() throws Exception {
		EmployeeRequest employee_req =new EmployeeRequest("21", "email", "ime prezime", OffsetDateTime.now(), "1234567891221", "jobRole1",
			  	"phoneNumber1", 21, 1L, 2L);
		this.mockMvc
				.perform(MockMvcRequestBuilders
				.post("/employee")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(employee_req)))
				.andExpect(status().isCreated());
	}
	
	@Test
	void testEmployeeWrongDataPOST() throws Exception {
		EmployeeRequest employee_req =new EmployeeRequest("21", "email", "ime prezime", OffsetDateTime.now(), "rr", "jobRole1",
			  	"phoneNumber1", 21, 1L, 2L);
		this.mockMvc
				.perform(MockMvcRequestBuilders
				.post("/employee")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(employee_req)))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testAllEmployeesGET() throws Exception {
		createTestEmployee();
		this.mockMvc.perform(MockMvcRequestBuilders
				    .get("/allemployees")
			        .contentType("application/json"))
			        .andExpect(status().isOk());
	}
	@Test
	public void testOneEmployeeeGET() throws Exception {
		createTestEmployee();
		this.mockMvc.perform(MockMvcRequestBuilders
	    .get("/employee")
	    .param("jmbg","9999999999999")
        .contentType("application/json"))
        .andExpect(status().isOk());
	}
	@Test
	public void testUpdateEmployeeePATCH() throws Exception {
		createTestEmployee();
		EmployeeDepartmentChange request = new EmployeeDepartmentChange("MARKETING", "9999999999999");
		this.mockMvc.perform(MockMvcRequestBuilders
	    .patch("/employee")
		.contentType("application/json")
		.content(objectMapper.writeValueAsString(request)))
		.andExpect(status().isOk());
	}
	@Test
	public void testDeleteEmployeeeDELETE() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders
	    .delete("/employee")
	    .param("id","1")
        .contentType("application/json"))
        .andExpect(status().isOk());
	}
	@Test
	public void testPutEmployeeePUT() throws Exception {
		EmployeeRequest employee_req =new EmployeeRequest("21", "testemail", "testname", OffsetDateTime.now(),
				"9999999999999", "jobRole1",
			  	"phoneNumber1", 21, 1L, 2L);
		this.mockMvc.perform(MockMvcRequestBuilders
			    .put("/employee/{id}",1)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(employee_req)))
				.andExpect(status().isOk());
	}

}

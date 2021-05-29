package com.outofoffice.outofoffice.rest;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;
import java.time.OffsetDateTime;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import javax.validation.Validator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.outofoffice.outofoffice.OutOfOfficeApplication;
import com.outofoffice.outofoffice.errorhandling.NotFoundException;
import com.outofoffice.outofoffice.errorhandling.NotSucesfullException;
import com.outofoffice.outofoffice.model.Employee;
import com.outofoffice.outofoffice.repository.EmployeeRepository;
import com.outofoffice.outofoffice.requestobjects.EmployeeDepartmentChange;
import com.outofoffice.outofoffice.requestobjects.EmployeeRequest;
import com.outofoffice.outofoffice.service.EmployeeService;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = OutOfOfficeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
class EmployeeControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	EmployeeService employeeService;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private JdbcTemplate jdbc;
	
	private Validator validator;
	
	
	@BeforeAll
	public void before() throws ScriptException, SQLException{
		//ScriptUtils.executeSqlScript(jdbc.getDataSource().getConnection(), new ClassPathResource("/insert.sql"));
	}
	private EmployeeRequest createTestEmployee() throws Exception {
		EmployeeRequest employee_req = new EmployeeRequest(21L, "test@gmail.com", "testname", OffsetDateTime.now(),
				"9999999999999", "jobRole1", "phoneNumber1", 21, "test", "test", 1L, 2L);
		this.mockMvc.perform(MockMvcRequestBuilders.post("/employee").contentType("application/json")
				.content(objectMapper.writeValueAsString(employee_req))).andExpect(status().isCreated());
		return employee_req;
	}
 
	@AfterAll
	public void after() throws ScriptException, SQLException{
		ScriptUtils.executeSqlScript(jdbc.getDataSource().getConnection(), new ClassPathResource("/drop.sql"));
	}
	@Test
	void testEmployeesPOST() throws Exception {
		createTestEmployee();
	}

	@Test
	void testEmployeeWrongDataPOST() throws Exception {
		EmployeeRequest employee_req = new EmployeeRequest(21L, "test@gmail.com", "ime prezime", OffsetDateTime.now(),
				"rr", "jobRole1", "phoneNumber1", 21,"test", "test", 1L, 2L);
		this.mockMvc.perform(MockMvcRequestBuilders.post("/employee").contentType("application/json")
				.content(objectMapper.writeValueAsString(employee_req))).andExpect(status().isBadRequest());
	}

	@Test
	public void testAllEmployeesGET() throws Exception {
		createTestEmployee();
		this.mockMvc.perform(MockMvcRequestBuilders.get("/allemployees").contentType("application/json"))
				.andExpect(status().isOk());
	}

	@Test
	public void testOneEmployeeeGET() throws Exception {
		createTestEmployee();
		this.mockMvc.perform(
				MockMvcRequestBuilders.get("/employee").param("jmbg", "9999999999999").contentType("application/json"))
				.andExpect(status().isOk());
	}

	@Test
	public void testOneEmployeeeNotFoundGET() throws Exception {
		createTestEmployee();
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/employee").param("jmbg", "6666666666666")
						.contentType("application/json"))
				.andExpect(status().isNotFound())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundException));
	}

	@Test
	public void testUpdateEmployeeePATCH() throws Exception {
		createTestEmployee();
		EmployeeDepartmentChange request = new EmployeeDepartmentChange("MARKETING", "9999999999999");
		this.mockMvc.perform(MockMvcRequestBuilders.patch("/employee").contentType("application/json")
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isOk());
	}

	@Test
	public void testDeleteEmployeeeDELETE() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.delete("/employee").param("id", "2").contentType("application/json"))
				.andExpect(status().isOk());
	}

	@Test
	public void testDeleteEmployeeeNotFoundDELETE() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.delete("/employee").param("id", "100000")
						.contentType("application/json"))
				.andExpect(status().isNotFound())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundException));
	}

	@Test
	public void testPutEmployeeePUT() throws Exception {
		EmployeeRequest employee_req = createTestEmployee();
		this.mockMvc.perform(MockMvcRequestBuilders.put("/employee/{id}", 2).contentType("application/json")
				.content(objectMapper.writeValueAsString(employee_req))).andExpect(status().isOk());
	}

	@Test
	public void testPutEmployeeeNotFoundPUT() throws Exception {
		EmployeeRequest employee_req = createTestEmployee();
		this.mockMvc
				.perform(MockMvcRequestBuilders.put("/employee/{id}", 1000).contentType("application/json")
						.content(objectMapper.writeValueAsString(employee_req)))
				.andExpect(status().isNotFound())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundException));
	}

	@Test
	public void testPutEmployeeeDepartmentWrongiDPUT() throws Exception {
		EmployeeRequest employee_req = new EmployeeRequest(21L, "test@gmail.com", "testname", OffsetDateTime.now(),
				"9999999999999", "jobRole1", "phoneNumber1", 21, "test", "test",15L, 2L);
		this.mockMvc
				.perform(MockMvcRequestBuilders.put("/employee/{id}", 3).contentType("application/json")
						.content(objectMapper.writeValueAsString(employee_req)))
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof NotSucesfullException));
	}
	@Test
	public void testComunicationWithLeaveRequestGET() throws Exception {
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/employee/{id}/{days}",3,5)
				.contentType("application/json"))
				.andExpect(status().isOk());
	}

	@Test
	public void testComunicationWithNotificationRequestGET() throws Exception {
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/getAllEmployeesNames")
				.contentType("application/json"))
				.andExpect(status().isOk());
	}
}

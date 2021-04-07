package com.outofoffice.outofoffice.rest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;

import javax.transaction.Transactional;
import javax.validation.Validator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.outofoffice.outofoffice.model.Department;
import com.outofoffice.outofoffice.requestobjects.LoginRequest;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = OutOfOfficeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
@TestInstance(Lifecycle.PER_CLASS)
class DepartmentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	private Validator validator;
	
	@Autowired
	private JdbcTemplate jdbc;
	
	@BeforeAll
	public void before() throws ScriptException, SQLException{
		//ScriptUtils.executeSqlScript(jdbc.getDataSource().getConnection(), new ClassPathResource("/drop.sql"));
		ScriptUtils.executeSqlScript(jdbc.getDataSource().getConnection(), new ClassPathResource("/insert.sql"));
	}
	@AfterAll
	public void after() throws ScriptException, SQLException{
		ScriptUtils.executeSqlScript(jdbc.getDataSource().getConnection(), new ClassPathResource("/drop.sql"));
	}
 
	private Department createTestDepartment() throws Exception {
		Department dep_req = new Department();
		dep_req.setCode("NEWDEPARTMENT"); dep_req.setDisplayName("New Department"); dep_req.setEmpAllowedNum(6);
		this.mockMvc.perform(MockMvcRequestBuilders.post("/department").contentType("application/json")
				.content(objectMapper.writeValueAsString(dep_req))).andExpect(status().isCreated());
		return dep_req;
	}

	@Test
	void testDeletePOST() throws Exception {
		createTestDepartment();
	}

	@Test
	public void testAllDepartmentGET() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/getAllDepartments").contentType("application/json"))
				.andExpect(status().isCreated());
	}

	@Test
	public void testDepartmentNotFoundDELETE() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.delete("/deleteDepartment/{id}",200)
						.contentType("application/json"))
				.andExpect(status().isNotFound())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundException));
	}


	@Test
	public void testDeleteDepartmentDLETE() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.delete("/deleteDepartment/{id}",2).contentType("application/json"))
				.andExpect(status().isOk());
	}

	@Test
	public void testPutDepartmentUT() throws Exception {
		Department dep_req = createTestDepartment();
		this.mockMvc.perform(MockMvcRequestBuilders.put("/changeDepartment/{id}", 1).contentType("application/json")
				.content(objectMapper.writeValueAsString(dep_req))).andExpect(status().isOk());
	}

	@Test
	public void testPutDeleteNotFoundPUT() throws Exception {
		Department dep_req = createTestDepartment();
		this.mockMvc
				.perform(MockMvcRequestBuilders.put("/changeDepartment/{id}", 1000).contentType("application/json")
						.content(objectMapper.writeValueAsString(dep_req)))
				.andExpect(status().isNotFound())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundException));
	}
}

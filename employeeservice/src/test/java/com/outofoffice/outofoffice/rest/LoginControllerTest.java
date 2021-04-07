package com.outofoffice.outofoffice.rest;

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
import com.outofoffice.outofoffice.requestobjects.LoginRequest;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OutOfOfficeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
@TestInstance(Lifecycle.PER_CLASS)
class LoginControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	private Validator validator;
	
	@Autowired
	private JdbcTemplate jdbc;
	
	@BeforeAll
	public void before() throws ScriptException, SQLException{
		ScriptUtils.executeSqlScript(jdbc.getDataSource().getConnection(), new ClassPathResource("/insert.sql"));
	}
	@AfterAll
	public void after() throws ScriptException, SQLException{
		ScriptUtils.executeSqlScript(jdbc.getDataSource().getConnection(), new ClassPathResource("/drop.sql"));
	}
 
	private LoginRequest createTestAuth() throws Exception {
		LoginRequest login_req = new LoginRequest("pass106", "username106",2L);
		this.mockMvc.perform(MockMvcRequestBuilders.post("/register").contentType("application/json")
				.content(objectMapper.writeValueAsString(login_req))).andExpect(status().isCreated());
		return login_req;
	}

	@Test
	void testAuthPOST() throws Exception {
		createTestAuth();
	}

	@Test
	public void testAllAuthGET() throws Exception {
		//createTestAuth();
		this.mockMvc.perform(MockMvcRequestBuilders.get("/getAuthData").contentType("application/json"))
				.andExpect(status().isCreated());
	}

	@Test
	public void testAuthNotFoundPOST() throws Exception {
		LoginRequest login_req = new LoginRequest("username1", "password1",10L);
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/register").content(objectMapper.writeValueAsString(login_req))
						.contentType("application/json"))
				.andExpect(status().isNotFound())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundException));
	}


	@Test
	public void testDeleteAuthDLETE() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.delete("/deleteAuth/{id}", 1).contentType("application/json"))
				.andExpect(status().isOk());
	}

	@Test
	public void testPutAuthPUT() throws Exception {
		LoginRequest auth_req = createTestAuth();
		this.mockMvc.perform(MockMvcRequestBuilders.put("/changeAuth/{id}", 1).contentType("application/json")
				.content(objectMapper.writeValueAsString(auth_req))).andExpect(status().isOk());
	}

	@Test
	public void testPutAuthNotFoundPUT() throws Exception {
		LoginRequest auth_req = new LoginRequest("usss", "pass",80L);
		this.mockMvc
				.perform(MockMvcRequestBuilders.put("/changeAuth/{id}", 1000).contentType("application/json")
						.content(objectMapper.writeValueAsString(auth_req)))
				.andExpect(status().isNotFound())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundException));
	}


}

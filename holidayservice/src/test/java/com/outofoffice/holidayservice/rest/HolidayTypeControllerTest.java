package com.outofoffice.holidayservice.rest;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.outofoffice.holidayservice.repository.EmployeeRepository;
//import com.outofoffice.holidayservice.repository.HolidayRepository;
//import com.outofoffice.holidayservice.repository.HolidayTypeRepository;
//import com.outofoffice.holidayservice.requestobjects.HolidayTypeRequest;

@SpringBootTest
@AutoConfigureMockMvc
class HolidayTypeControllerTest {
//	@Autowired
//	private MockMvc mockMvc;
//	@Autowired
//	private ObjectMapper objectMapper;
//	@Resource
//	private HolidayTypeRepository holidayTypeRepository;
//	@Resource
//	private HolidayRepository holidayRepository;
//	@Resource
//	private EmployeeRepository employeeRepository;
//	
//	@Test
//	public void postHolidayType() throws Exception {
//		
//		HolidayTypeRequest holidayType = new HolidayTypeRequest("proba1", "proba2", "proba3", "proba4");
//		
//		this.mockMvc.perform(MockMvcRequestBuilders
//				    .post("/holidayType")
//			        .contentType("application/json")
//			        .content(objectMapper.writeValueAsString(holidayType)))
//			        .andExpect(status().isOk());
//	}
//	
//	@Test
//	public void postHolidayTypeFail2() throws Exception {
//		
//		this.mockMvc.perform(MockMvcRequestBuilders
//			    .patch("/holidayType/{id}", "1")
//		        .contentType("application/json")
//		        .content(objectMapper.writeValueAsString(new HolidayTypeRequest("p", "proba2", "proba3", "proba4"))))
//		        .andExpect(status().isBadRequest());
//	}
//	
//	@Test
//	public void patchHolidayType() throws Exception {
//		
//		HolidayTypeRequest holidayType = new HolidayTypeRequest("proba11", "proba22", "proba33", "proba44");
//		
//		this.mockMvc.perform(MockMvcRequestBuilders
//				    .patch("/holidayType/{id}", "1")
//			        .contentType("application/json")
//			        .content(objectMapper.writeValueAsString(holidayType)))
//			        .andExpect(status().isOk());
//	}
//	
//	@Test
//	public void patchHolidayTypeFail() throws Exception {
//		
//		HolidayTypeRequest holidayType = new HolidayTypeRequest();
//		holidayType.setCode("proba");
//		holidayType.setText("probni text");
//		holidayType.setType("tip odmora");
//		
//		this.mockMvc.perform(MockMvcRequestBuilders
//				    .patch("/holidayType/{id}", "1")
//			        .contentType("application/json")
//			        .content(objectMapper.writeValueAsString(holidayType)))
//			        .andExpect(status().isBadRequest());
//		
//		holidayType.setDisplayName("Dodato ime");
//		
//		this.mockMvc.perform(MockMvcRequestBuilders
//			    .patch("/holidayType/{id}", "85")
//		        .contentType("application/json")
//		        .content(objectMapper.writeValueAsString(holidayType)))
//		        .andExpect(status().isNotFound());
//	}
//	
//	@Test
//	public void deleteHolidayType() throws Exception {
//		
//		this.mockMvc.perform(MockMvcRequestBuilders
//				    .delete("/holidayType/{id}", "2")
//			        .contentType("application/json"))
//			        .andExpect(status().isOk());
//	}
//	
//	@Test
//	public void deleteHolidayTypeFail() throws Exception {
//		
//		this.mockMvc.perform(MockMvcRequestBuilders
//				    .delete("/holidayType/{id}", "52")
//			        .contentType("application/json"))
//			        .andExpect(status().isNotFound());
//	}
//	
//	@Test
//	public void getHolidayType() throws Exception {
//		
//		this.mockMvc.perform(MockMvcRequestBuilders
//				    .get("/listHolidayTypes")
//			        .contentType("application/json"))
//			        .andExpect(status().isOk());
//	}
	
	
}

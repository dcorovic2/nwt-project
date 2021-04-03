package com.outofoffice.holidayservice.rest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.Month;

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
import com.outofoffice.holidayservice.requestobjects.HolidayRequest;

@SpringBootTest
@AutoConfigureMockMvc
class HolidayControllerTest {
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
	public void postHoliday() throws Exception {
		
		LocalDate start = LocalDate.of(2021, Month.OCTOBER, 8);
    	LocalDate end = LocalDate.of(2021, Month.OCTOBER, 15);
		HolidayRequest holidayRequest = new HolidayRequest(start, end);
		
		this.mockMvc.perform(MockMvcRequestBuilders
				    .post("/holiday/{holidayTypeID}/{employeeID}", "1", "1")
			        .contentType("application/json")
			        .content(objectMapper.writeValueAsString(holidayRequest)))
			        .andExpect(status().isOk());
	}
	
	@Test
	public void postHolidayFail1() throws Exception {
		
		LocalDate start = LocalDate.of(2021, Month.OCTOBER, 8);
    	LocalDate end = LocalDate.of(2021, Month.OCTOBER, 15);
		HolidayRequest holidayRequest = new HolidayRequest(start, end);
		
		this.mockMvc.perform(MockMvcRequestBuilders
				    .post("/holiday/{holidayTypeID}/{employeeID}", "1", "100")
			        .contentType("application/json")
			        .content(objectMapper.writeValueAsString(holidayRequest)))
			        .andExpect(status().isNotFound());
	}
	
	@Test
	public void postHolidayFail2() throws Exception {
		
		LocalDate start = LocalDate.of(2021, Month.OCTOBER, 8);
    	LocalDate end = LocalDate.of(2021, Month.OCTOBER, 15);
		HolidayRequest holidayRequest = new HolidayRequest(start, end);
		
		this.mockMvc.perform(MockMvcRequestBuilders
				    .post("/holiday/{holidayTypeID}/{employeeID}", "100", "1")
			        .contentType("application/json")
			        .content(objectMapper.writeValueAsString(holidayRequest)))
			        .andExpect(status().isNotFound());
	}
	
	@Test
	public void postHolidayFail3() throws Exception {
		
		HolidayRequest holidayRequest = new HolidayRequest(null, null);
		
		this.mockMvc.perform(MockMvcRequestBuilders
				    .post("/holiday/{holidayTypeID}/{employeeID}", "100", "1")
			        .contentType("application/json")
			        .content(objectMapper.writeValueAsString(holidayRequest)))
			        .andExpect(status().isBadRequest());
	}
	
	@Test
	public void patchHoliday() throws Exception {
		
		LocalDate start = LocalDate.of(2021, Month.OCTOBER, 8);
    	LocalDate end = LocalDate.of(2021, Month.OCTOBER, 15);
		HolidayRequest holidayRequest = new HolidayRequest(start, end);
		
		this.mockMvc.perform(MockMvcRequestBuilders
				    .patch("/holiday/{holidayId}", "1")
			        .contentType("application/json")
			        .content(objectMapper.writeValueAsString(holidayRequest)))
			        .andExpect(status().isOk());
	}
	
	@Test
	public void patchHolidayFail() throws Exception {
		
		LocalDate start = LocalDate.of(2021, Month.OCTOBER, 8);
    	LocalDate end = LocalDate.of(2021, Month.OCTOBER, 15);
		HolidayRequest holidayRequest = new HolidayRequest(start, end);
		
		this.mockMvc.perform(MockMvcRequestBuilders
				    .patch("/holiday/{holidayId}", "142")
			        .contentType("application/json")
			        .content(objectMapper.writeValueAsString(holidayRequest)))
			        .andExpect(status().isNotFound());
	}
	
	@Test
	public void deleteHoliday() throws Exception {
		
		this.mockMvc.perform(MockMvcRequestBuilders
				    .delete("/holiday/{holidayId}", "1")
			        .contentType("application/json"))
			        .andExpect(status().isOk());
	}
	
	@Test
	public void deleteHolidayFail() throws Exception {
		
		this.mockMvc.perform(MockMvcRequestBuilders
				    .delete("/holiday/{holidayId}", "152")
			        .contentType("application/json"))
			        .andExpect(status().isNotFound());
	}
	
	@Test
	public void getHoliday() throws Exception {
		
		this.mockMvc.perform(MockMvcRequestBuilders
				    .get("/holiday/{employeeId}", "1")
			        .contentType("application/json"))
			        .andExpect(status().isOk());
	}
	
	@Test
	public void getHolidayFail() throws Exception {
		
		this.mockMvc.perform(MockMvcRequestBuilders
				    .get("/holiday/{employeeId}", "55")
			        .contentType("application/json"))
			        .andExpect(status().isNotFound());
	}

}

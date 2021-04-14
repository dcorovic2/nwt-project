package com.outofoffice.holidayservice.rest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.Month;

import javax.annotation.Resource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.ResponseActions;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.outofoffice.holidayservice.repository.EmployeeRepository;
//import com.outofoffice.holidayservice.repository.HolidayRepository;
//import com.outofoffice.holidayservice.repository.HolidayTypeRepository;
//import com.outofoffice.holidayservice.requestobjects.HolidayRequest;
//import com.outofoffice.holidayservice.service.HolidayService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withBadRequest;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;

@SpringBootTest
@AutoConfigureMockMvc
class HolidayControllerTest {
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
	
	private static final String URL = "http://employee-service/getAllEmployeesNames";
	private static final String URL1 = "http://employee-service/getAllEmployeesNamees";
    private MockRestServiceServer mockRestServiceServer;
    private RestTemplate restTemplate;
    
    @BeforeEach
    public void setUp() {
        restTemplate = new RestTemplate();
        mockRestServiceServer = MockRestServiceServer.createServer(restTemplate);
    }
    
//    @Test
//    public void response4xx() {
//    	//MS is available, but url isn't correct
//        mockRestServiceServer
//            .expect(requestTo(URL1))
//            .andExpect(method(HttpMethod.GET))
//            .andRespond(withBadRequest());
//
//        ResponseEntity responseEntity = consumeWebService(URL1, Object.class);
//
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//    }
//
//    @Test
//    public void response5xx() {
//    	//MS isn't available
//        mockRestServiceServer
//            .expect(requestTo(URL))
//            .andExpect(method(HttpMethod.GET))
//            .andRespond(withServerError());
//
//        ResponseEntity responseEntity = consumeWebService(URL, Object.class);
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    <T> ResponseEntity consumeWebService(String url, Class<T> responseType) {
//        try {
//            return restTemplate.getForEntity(url, responseType);
//        } catch (RestClientResponseException e) {
//            return ResponseEntity
//                .status(e.getRawStatusCode())
//                .body(e.getResponseBodyAsString());
//        }
//    }
//    
//    @Test
//	public void postHoliday() throws Exception {
//		
//		LocalDate start = LocalDate.of(2021, Month.OCTOBER, 8);
//    	LocalDate end = LocalDate.of(2021, Month.OCTOBER, 15);
//		HolidayRequest holidayRequest = new HolidayRequest(start, end);
//		
//		this.mockMvc.perform(MockMvcRequestBuilders
//				    .post("/holiday/{holidayTypeID}", "1")
//			        .contentType("application/json")
//			        .content(objectMapper.writeValueAsString(holidayRequest)))
//			        .andExpect(status().isOk());
//	}
//    
//    @Test
//	public void postHolidayFail() throws Exception {
//		
//		LocalDate start = LocalDate.of(2021, Month.OCTOBER, 8);
//    	LocalDate end = LocalDate.of(2021, Month.OCTOBER, 15);
//		HolidayRequest holidayRequest = new HolidayRequest(start, end);
//		
//		this.mockMvc.perform(MockMvcRequestBuilders
//				    .post("/holiday/{holidayTypeID}", "156")
//			        .contentType("application/json")
//			        .content(objectMapper.writeValueAsString(holidayRequest)))
//			        .andExpect(status().isNotFound());
//	}
//    
//	@Test
//	public void postDefaultHolidayFail1() throws Exception {
//		
//		LocalDate start = LocalDate.of(2021, Month.OCTOBER, 8);
//    	LocalDate end = LocalDate.of(2021, Month.OCTOBER, 15);
//		HolidayRequest holidayRequest = new HolidayRequest(start, end);
//		
//		this.mockMvc.perform(MockMvcRequestBuilders
//				    .post("/defaultHoliday/{holidayTypeID}", "1")
//			        .contentType("application/json")
//			        .content(objectMapper.writeValueAsString(holidayRequest)))
//			        .andExpect(status().isInternalServerError());
//	}
//	
//	@Test
//	public void updateHoliday() throws Exception {
//		
//		LocalDate start = LocalDate.of(2021, Month.OCTOBER, 8);
//    	LocalDate end = LocalDate.of(2021, Month.OCTOBER, 15);
//		HolidayRequest holidayRequest = new HolidayRequest(start, end);
//		
//		this.mockMvc.perform(MockMvcRequestBuilders
//				    .patch("/holiday/{holidayTypeID}/{employeeID}/{firstAndLastName}", "1", "1", "Dalila")
//			        .contentType("application/json")
//			        .content(objectMapper.writeValueAsString(holidayRequest)))
//			        .andExpect(status().isOk());
//	}
//	
//	@Test
//	public void updateHolidayFail() throws Exception {
//		
//		LocalDate start = LocalDate.of(2021, Month.OCTOBER, 8);
//    	LocalDate end = LocalDate.of(2021, Month.OCTOBER, 15);
//		HolidayRequest holidayRequest = new HolidayRequest(start, end);
//		
//		this.mockMvc.perform(MockMvcRequestBuilders
//				    .patch("/holiday/{holidayTypeID}/{employeeID}/{firstAndLastName}", "153", "1", "Dalila")
//			        .contentType("application/json")
//			        .content(objectMapper.writeValueAsString(holidayRequest)))
//			        .andExpect(status().isNotFound());
//	}
//	
//	@Test
//	public void updateHoliday2() throws Exception {
//		
//		LocalDate start = LocalDate.of(2021, Month.OCTOBER, 8);
//    	LocalDate end = LocalDate.of(2021, Month.OCTOBER, 13);
//		HolidayRequest holidayRequest = new HolidayRequest(start, end);
//		
//		this.mockMvc.perform(MockMvcRequestBuilders
//				    .post("/holiday/{holidayId}", "1")
//			        .contentType("application/json")
//			        .content(objectMapper.writeValueAsString(holidayRequest)))
//			        .andExpect(status().isOk());
//	}
	
//	@Test
//	public void getListOfEmployees() throws Exception {
//		
//		this.mockMvc.perform(MockMvcRequestBuilders
//				    .get("/getlistofemployees/{holidayTypeId}", "1")
//			        .contentType("application/json"))
//			        .andExpect(status().isOk());
//	}

//	@Test
//	public void patchHoliday() throws Exception {
//		
//		LocalDate start = LocalDate.of(2021, Month.OCTOBER, 8);
//    	LocalDate end = LocalDate.of(2021, Month.OCTOBER, 15);
//		HolidayRequest holidayRequest = new HolidayRequest(start, end);
//		
//		this.mockMvc.perform(MockMvcRequestBuilders
//				    .patch("/holiday/{holidayId}", "1")
//			        .contentType("application/json")
//			        .content(objectMapper.writeValueAsString(holidayRequest)))
//			        .andExpect(status().isOk());
//	}

//	@Test
//	public void patchHolidayFail() throws Exception {
//		
//		LocalDate start = LocalDate.of(2021, Month.OCTOBER, 8);
//    	LocalDate end = LocalDate.of(2021, Month.OCTOBER, 15);
//		HolidayRequest holidayRequest = new HolidayRequest(start, end);
//		
//		this.mockMvc.perform(MockMvcRequestBuilders
//				    .patch("/holiday/{holidayId}", "142")
//			        .contentType("application/json")
//			        .content(objectMapper.writeValueAsString(holidayRequest)))
//			        .andExpect(status().isNotFound());
//	}

//	@Test
//	public void deleteHoliday() throws Exception {
//		
//		this.mockMvc.perform(MockMvcRequestBuilders
//				    .delete("/holiday/{holidayId}", "1")
//			        .contentType("application/json"))
//			        .andExpect(status().isOk());
//	}
//	
//	@Test
//	public void deleteHolidayFail() throws Exception {
//		
//		this.mockMvc.perform(MockMvcRequestBuilders
//				    .delete("/holiday/{holidayId}", "152")
//			        .contentType("application/json"))
//			        .andExpect(status().isNotFound());
//	}

//	@Test
//	public void getHoliday() throws Exception {
//		
//		this.mockMvc.perform(MockMvcRequestBuilders
//				    .get("/holiday/{employeeId}", "1")
//			        .contentType("application/json"))
//			        .andExpect(status().isOk());
//	}
//	
//	@Test
//	public void getHolidayFail() throws Exception {
//		
//		this.mockMvc.perform(MockMvcRequestBuilders
//				    .get("/holiday/{employeeId}", "55")
//			        .contentType("application/json"))
//			        .andExpect(status().isNotFound());
//	}

}

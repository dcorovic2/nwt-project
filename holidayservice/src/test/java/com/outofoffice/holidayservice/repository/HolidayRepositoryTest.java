package com.outofoffice.holidayservice.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.outofoffice.holidayservice.model.Employee;
import com.outofoffice.holidayservice.model.Holiday;
import com.outofoffice.holidayservice.model.HolidayType;

@SpringBootTest
@AutoConfigureMockMvc
class HolidayRepositoryTest {	
	@Resource
	private HolidayTypeRepository holidayTypeRepository;
	@Resource
	private HolidayRepository holidayRepository;
	@Resource
	private EmployeeRepository employeeRepository;

	@Test
    public void saveHolidayinDB() {    	
    	
//    	HolidayType holidayType = new HolidayType("code1", "Godisnji odmor", "godisnji", "Godisnji odmor");	
//    	holidayTypeRepository.save(holidayType);
//    	
////		Employee employee = new Employee("Dalila Corovic");
////		employeeRepository.save(employee);
//		
//		List<Employee> listEmployees  = new ArrayList<>();
//		listEmployees.add(employee);
//		
//		LocalDate start = LocalDate.of(2020, Month.OCTOBER, 8);
//		LocalDate end = LocalDate.of(2020, Month.OCTOBER, 20);
//		
//		Holiday holiday = new Holiday(start, end, listEmployees, holidayType);
//		holidayRepository.save(holiday);
//        
//        Holiday holidayType2 = holidayRepository.findById(holiday.getId()).get();
//        assertEquals(2, holidayType2.getId());
    }
}

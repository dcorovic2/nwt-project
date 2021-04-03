package com.outofoffice.holidayservice.requestobjects;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.outofoffice.holidayservice.repository.EmployeeRepository;
import com.outofoffice.holidayservice.repository.HolidayRepository;
import com.outofoffice.holidayservice.repository.HolidayTypeRepository;

@SpringBootTest
@AutoConfigureMockMvc
class HolidayRequestTest {
private Validator validator;
	
	@Resource
	private HolidayTypeRepository holidayTypeRepository;
	@Resource
	private HolidayRepository holidayRepository;
	@Resource
	private EmployeeRepository employeeRepository;

	@BeforeEach
	void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
    public void testHolidayRequest() {
    	
    	HolidayRequest holidayr = new HolidayRequest();
    	LocalDate start = LocalDate.of(2021, Month.OCTOBER, 8);
    	LocalDate end = LocalDate.of(2021, Month.OCTOBER, 8);
    	holidayr.setStartDate(start);
    	holidayr.setEndDate(end);
    	
        Set<ConstraintViolation<HolidayRequest>> violations = validator.validate(holidayr);
        assertTrue(violations.isEmpty());
    }

}

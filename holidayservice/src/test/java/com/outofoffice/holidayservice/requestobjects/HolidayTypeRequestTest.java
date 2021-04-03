package com.outofoffice.holidayservice.requestobjects;

import static org.junit.jupiter.api.Assertions.*;

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
class HolidayTypeRequestTest {
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
	public void validationHolidayTypeFail() throws Exception {
		
		HolidayTypeRequest holidayType = new HolidayTypeRequest("proba1", "p", "proba3", "proba4");
		
		Set<ConstraintViolation<HolidayTypeRequest>> violations = validator.validate(holidayType);
        assertFalse(violations.isEmpty());
	}
}

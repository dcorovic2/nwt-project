package com.outofoffice.holidayservice.repository;

import static org.junit.jupiter.api.Assertions.*;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.outofoffice.holidayservice.model.HolidayType;

@SpringBootTest
@AutoConfigureMockMvc
class HolidayTypeRepositoryTest {
	@Resource
	private HolidayTypeRepository holidayTypeRepository;
	@Resource
	private HolidayRepository holidayRepository;
	@Resource
	private EmployeeRepository employeeRepository;

    @Test
    public void saveHolidayTypeinDB() {    	
    	
        HolidayType holidayType = new HolidayType("kod", "Drzavni", "Drzavni praznik", "Drazvni praznik");
        holidayTypeRepository.save(holidayType);
        
        HolidayType holidayType2 = holidayTypeRepository.findById(holidayType.getId()).get();
        assertEquals("Drzavni", holidayType2.getDisplayName());
    }

}

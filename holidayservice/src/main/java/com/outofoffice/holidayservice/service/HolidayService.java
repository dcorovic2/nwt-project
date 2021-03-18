package com.outofoffice.holidayservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.outofoffice.holidayservice.model.Holiday;
import com.outofoffice.holidayservice.repository.HolidayRepository;


@Service
public class HolidayService {
	
	private final HolidayRepository holidayRepository;

	public HolidayService(HolidayRepository holidayRepository) {
		this.holidayRepository =  holidayRepository;
	}

	public List<Holiday> insertBulkHolidays(List<Holiday> holidays) {
		List<Holiday> insertedHolidays = new ArrayList<Holiday>();
		holidays.forEach(holiday ->  {
			insertedHolidays.add(insertHoliday(holiday));
		});
		
		return insertedHolidays;
	}
	
	public Holiday insertHoliday(Holiday holiday) {
		return holidayRepository.save(holiday);
	}

	public Holiday updateHoliday(Holiday holiday) {
		return holidayRepository.save(holiday);
	}
	
	public Long deleteHoliday(Long id) throws Exception {
		final Optional<Holiday> holidayForDelete =  holidayRepository.findById(id);
		if (!holidayForDelete.isPresent()) {
			throw new Exception("Holiday with given id does not exist");
		}
		holidayRepository.delete(holidayForDelete.get());
		return holidayForDelete.get().getId();
	}
	public List<Holiday> getHolidaysByName(String name) {
		return holidayRepository.getAllHolidaysWithGivenName(name);
	}
}

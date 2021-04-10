package com.outofoffice.holidayservice.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.outofoffice.holidayservice.errorhandling.NoDataException;
import com.outofoffice.holidayservice.errorhandling.NotFoundException;
import com.outofoffice.holidayservice.model.HolidayType;
import com.outofoffice.holidayservice.repository.HolidayTypeRepository;
import com.outofoffice.holidayservice.requestobjects.HolidayTypeRequest;

@Service
public class HolidayTypeService {
	private final HolidayTypeRepository holidayTypeRepository;

	public HolidayTypeService(HolidayTypeRepository holidayTypeRepository) {
		this.holidayTypeRepository =  holidayTypeRepository;
	}
	
	public ResponseEntity<?> insertHolidayType(HolidayTypeRequest holidayType) {
		HolidayType holiday2 = new HolidayType(holidayType.getCode(), holidayType.getDisplayName(), holidayType.getText(), holidayType.getType());	
		HolidayType newholiday = holidayTypeRepository.save(holiday2);
			
		return new ResponseEntity<>(newholiday, HttpStatus.OK);
	}
	
	public ResponseEntity<?> updateHolidayType(HolidayTypeRequest holiday, long id){
		String tmp = id + "";
		HolidayType updatedHolidayType =  holidayTypeRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(tmp, "HolidayType", "ID", ""));
			 
		updatedHolidayType.setCode(holiday.getCode());
		updatedHolidayType.setDisplayName(holiday.getDisplayName());
		updatedHolidayType.setText(holiday.getText());
		updatedHolidayType.setType(holiday.getType());
		HolidayType upholy = holidayTypeRepository.save(updatedHolidayType);
		
		return new ResponseEntity<>(upholy, HttpStatus.OK);
	} 
	
	public ResponseEntity<?> deleteHolidayType(Long id) {
		String tmp = id + "";
		HolidayType holidayTypeForDelete =  holidayTypeRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(tmp, "HolidayType", "ID", ""));
			
		holidayTypeRepository.delete(holidayTypeForDelete);
		Long deletedHolidayType = holidayTypeForDelete.getId();
			
		return new ResponseEntity<Long>(deletedHolidayType, HttpStatus.OK);
	}

	public ResponseEntity<?> getAllHolidays() {
		List<HolidayType> holiday = holidayTypeRepository.findAll();
			
		if (holiday.isEmpty()) {
			throw new NoDataException();
		}
			
		return new ResponseEntity<>(holiday, HttpStatus.OK);
	}
}

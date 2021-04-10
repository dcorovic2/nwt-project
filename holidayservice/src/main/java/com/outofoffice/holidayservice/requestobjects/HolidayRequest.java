package com.outofoffice.holidayservice.requestobjects;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class HolidayRequest {
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
	
	public HolidayRequest(LocalDate startDate, LocalDate endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public HolidayRequest() {
		super();
	}
    
}

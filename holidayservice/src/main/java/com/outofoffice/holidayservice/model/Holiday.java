package com.outofoffice.holidayservice.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name = "holiday")
@Data
public class Holiday {
	
	@Id
    @GeneratedValue(generator = "HolidayIdGenerator", strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "start_date")
    private LocalDate startDate;
	
    @Column(name = "end_date")
    private LocalDate endDate;
	
    
	public Holiday() {}
	
	public Holiday(LocalDate startDate, LocalDate endDate, List<Employee> employees, HolidayType holidayType) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.employees = employees;
		this.holidayType = holidayType;
	}

	@ManyToMany(cascade = {CascadeType.MERGE})
	@JoinTable(name = "holiday_employee",
	        joinColumns = @JoinColumn(name = "holiday_id"),
	        inverseJoinColumns = @JoinColumn(name = "employee_id")
	    )
	private List<Employee> employees = new ArrayList<>(); 	
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "holiday_type_id")
	private HolidayType holidayType;	
	
}

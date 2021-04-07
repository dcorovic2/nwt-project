package com.outofoffice.holidayservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "employee")
@Data
public class Employee {
    
	@Id
    @GeneratedValue(generator = "EmployeeIdGenerator", strategy = GenerationType.AUTO)
	private long interni_id;
	
	@Column(name = "employee_id")
    private Long id;
	
	@Column(name = "firstnameLastName")
	private String firstnameLastName;
	
	
	public Employee() {}

	public Employee(Long id, String firstnameLastName) {
		super();
		this.id = id;
		this.firstnameLastName = firstnameLastName;
	}
	
}

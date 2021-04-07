package com.outofoffice.holidayservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Table(name = "employee")
@Data
public class Employee {
    
	@Id
    @GeneratedValue(generator = "EmployeeIdGenerator", strategy = GenerationType.AUTO)
    private long id;
	
	@Column(name = "firstnameLastName")
	private String firstnameLastName;
	
	
	public Employee() {}

	public Employee(String firstnameLastName) {
		super();
		this.firstnameLastName = firstnameLastName;
	}
	
}

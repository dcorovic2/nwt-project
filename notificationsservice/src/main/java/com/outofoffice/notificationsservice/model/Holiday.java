package com.outofoffice.notificationsservice.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn; 
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table(name="holiday")
@Data
public class Holiday {
	
	@Id
    @GeneratedValue(generator = "HolidayIdGenerator",strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(name="display_name")
	private String displayName;
    
	@Column(name="name")
	private String name;

	@Column(name="type")
	private String type;
	
	
	public Holiday(String displayName, String name, String type, List<Employee> employees) {
		this.displayName = displayName;
		this.name = name; 
		this.type = type;
		this.employees = employees;
	}
	
	@ManyToMany()
	@JoinTable(name = "holiday_employee",
	        joinColumns = @JoinColumn(name = "holiday_id"),
	        inverseJoinColumns = @JoinColumn(name = "employee_id")
	    )
	private List<Employee> employees = new ArrayList<>(); 

}

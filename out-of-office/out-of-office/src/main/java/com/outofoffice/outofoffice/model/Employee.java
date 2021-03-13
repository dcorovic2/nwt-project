package com.outofoffice.outofoffice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name= "employee")
@Data
public class Employee {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(name = "employee_seq_generator", sequenceName = "seq_employee", allocationSize = 1)
	private Long id;
	
	@Column(name="name")
	private String name;
	@Column(name="display_name")
	private String displayName;
	
}

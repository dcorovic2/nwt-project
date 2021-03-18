package com.outofoffice.holidayservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import javax.persistence.Column;


@Entity
@Table(name="department")
@Data
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="code")
	private String code;

	@Column(name="display_name")
	private String displayName;

	@Column(name="emp_allowed_num")
	private Integer empAllowedNum;

	@Column(name="name")
	private String name;
	
	public Department() {}


}

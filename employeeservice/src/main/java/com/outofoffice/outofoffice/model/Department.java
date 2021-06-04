package com.outofoffice.outofoffice.model;
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

	@Column(name="code", unique = true)
	private String code;

	@Column(name="display_name")
	private String displayName;

	@Column(name="emp_allowed_num")
	private Integer empAllowedNum;

	@Column(name="name")
	private String name;
	
	public Department() {}

	public Department(String code, String displayName, Integer empAllowedNum, String name) {
		super();
		this.code = code;
		this.displayName = displayName;
		this.empAllowedNum = empAllowedNum;
		this.name = name;
	}

}

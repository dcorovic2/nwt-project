package com.outofoffice.holidayservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Table(name="login")
@Data
public class Login {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="employee_id")
	private Long employeeId;

	@Column(name="password")
	private String password;

	@Column(name="username")
	private String username;
	
	public Login() {};
}

package com.outofoffice.outofoffice.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;

import lombok.Data;

@Entity
@Table(name="auth")
@Data
public class Auth {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="username",unique = true)
	private String username;
    
	@Column(name="password")
	private String password;
	
	@OneToOne
	@JoinColumn(name="employee_id",referencedColumnName = "id")
	private Employee employee;
	
	public Auth() {};
}

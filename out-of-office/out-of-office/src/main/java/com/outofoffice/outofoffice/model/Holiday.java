package com.outofoffice.outofoffice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="holiday")
@Data
public class Holiday {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="display_name")
	private String displayName;
    
	@Column(name="name")
	private String name;

	@Column(name="type")
	private String type;
	
	@Column(name="employe_id")
	private Long employeeId;
	
	public Holiday() {};
}

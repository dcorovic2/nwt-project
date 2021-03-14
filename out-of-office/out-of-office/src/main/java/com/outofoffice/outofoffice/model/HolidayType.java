package com.outofoffice.outofoffice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="holiday_type")
@Data
public class HolidayType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="code")
	private String code;

	@Column(name="display_name")
	private String displayName;

	@Column(name="name")
	private String name;
	
	@Column(name="type")
	private String type;
	
	public HolidayType() {};

}

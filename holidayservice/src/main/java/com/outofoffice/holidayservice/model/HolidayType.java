package com.outofoffice.holidayservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "holiday_type")
@Data
public class HolidayType {
	
	@Id
    @GeneratedValue(generator = "HolidayTypeIdGenerator", strategy = GenerationType.AUTO)
    private long id;
	
	@Column(name = "code")
	private String code;

	@Column(name = "display_name")
	private String displayName;

	@Column(name = "name")
	private String name;
	
	@Column(name = "type")
	private String type;
	
	public HolidayType() {};

	public HolidayType(String code, String displayName, String name, String type) {
		super();
		this.code = code;
		this.displayName = displayName;
		this.name = name;
		this.type = type;
	}
	
}

package com.outofoffice.outofoffice.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

import lombok.Data;

@Entity
@Table(name="leave_request")
@Data
public class LeaveRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="comment")
	private String comment;

	@Column(name="days_num")
	private Integer daysNum;

	@Column(name="employee_id")
	private Integer employeeId;

	@Column(name="end_date")
	private Date endDate;

	@Column(name="start_date")
	private Date startDate;

	@Column(name="status_id")
	private Integer statusId;

	@Column(name="type_id")
	private Integer typeId;
	
	public LeaveRequest() {};
}

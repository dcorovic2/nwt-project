package com.outofoffice.leaverequestservice.model;

import java.time.OffsetDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	@Column(name="employee_id")
	private int employeeId;
	
	@Column(name="days_num")
	private int daysNum;
	
	//@JsonFormat(pattern="dd.mm.yyyy",timezone = "UTC")
    @Column(name = "start_date")
    private OffsetDateTime startDate;
	
	//@JsonFormat(pattern="dd.mm.yyyy",timezone = "UTC")
    @Column(name = "end_date")
    private OffsetDateTime endDate;
	
	public LeaveRequest() {};

	public LeaveRequest(String comment, int daysNum, int employeeId, OffsetDateTime startDate, OffsetDateTime endDate,
			LeaveType leave_type, LeaveStatus leave_status, NotificationsType notifications_type) {
		super();
		//this.id = id;
		this.comment = comment;
		this.daysNum = daysNum;
		this.employeeId = employeeId;
		this.endDate = endDate;
		this.startDate = startDate;
		this.leave_type = leave_type;
		this.leave_status = leave_status;
		this.notifications_type = notifications_type;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "leave_type_id")
	private LeaveType leave_type;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "leave_status_id")
	private LeaveStatus leave_status;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "notifications_type_id")
	private NotificationsType notifications_type;
	
	
	
}

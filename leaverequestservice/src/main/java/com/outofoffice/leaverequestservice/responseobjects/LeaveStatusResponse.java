package com.outofoffice.leaverequestservice.responseobjects;

import java.time.LocalDate;

import com.outofoffice.leaverequestservice.model.LeaveStatus;
import com.outofoffice.leaverequestservice.model.LeaveType;
import com.outofoffice.leaverequestservice.model.NotificationsType;

public class LeaveStatusResponse {
	private Long id;
	private String comment;
	private Long employeeId;
	private int daysNum;
    private LocalDate startDate;
    private LocalDate endDate;
	private long restDaysNum;
	private LeaveType leave_type;
	private LeaveStatus leave_status;
	private NotificationsType notifications_type;
	private String employeeName; 
	
	
	public LeaveStatusResponse() {};

	public LeaveStatusResponse(String comment, int daysNum, Long employeeId, LocalDate startDate, LocalDate endDate,
			LeaveType leave_type, LeaveStatus leave_status, NotificationsType notifications_type, long restDaysNum, String employeeName) {
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
		this.restDaysNum = restDaysNum;
		this.employeeName = employeeName;
	}

	
	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public int getDaysNum() {
		return daysNum;
	}

	public void setDaysNum(int daysNum) {
		this.daysNum = daysNum;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public long getRestDaysNum() {
		return restDaysNum;
	}

	public void setRestDaysNum(long restDaysNum) {
		this.restDaysNum = restDaysNum;
	}

	public LeaveType getLeave_type() {
		return leave_type;
	}

	public void setLeave_type(LeaveType leave_type) {
		this.leave_type = leave_type;
	}

	public LeaveStatus getLeave_status() {
		return leave_status;
	}

	public void setLeave_status(LeaveStatus leave_status) {
		this.leave_status = leave_status;
	}

	public NotificationsType getNotifications_type() {
		return notifications_type;
	}

	public void setNotifications_type(NotificationsType notifications_type) {
		this.notifications_type = notifications_type;
	}
	
}

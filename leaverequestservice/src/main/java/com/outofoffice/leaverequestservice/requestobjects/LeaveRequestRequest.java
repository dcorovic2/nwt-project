package com.outofoffice.leaverequestservice.requestobjects;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.outofoffice.leaverequestservice.model.LeaveRequest;
import com.outofoffice.leaverequestservice.model.LeaveStatus;
import com.outofoffice.leaverequestservice.model.LeaveType;


import lombok.Data;

public class LeaveRequestRequest {
		//private Long id;
		@NotNull(message="Comment can not be null")
		@Size(min=3,max=200, message="Wrong comment size")
		private String comment;
		
		@Range(min=1, message="Days Number can not 0")
		private int daysNum;
		
		private Long employeeId;
		
		@NotNull(message="Start Date can not be null")
		private LocalDate startDate;
		
		private int typeId;
		
		public LeaveRequestRequest() {}

		public LeaveRequestRequest(String comment, int daysNum, Long employeeId, LocalDate starDate, int typeId) {
			super();
			//this.id = id;
			this.comment = comment;
			this.daysNum = daysNum;
			this.employeeId = employeeId;
			this.startDate = startDate;
			this.typeId = typeId;
		}
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}
		public int getDaysNum() {
			return daysNum;
		}
		public void setDaysNum(int daysNum) {
			this.daysNum = daysNum;
		}
		public Long getEmployeeId() {
			return employeeId;
		}
		public void setEmployeeId(Long employeeId) {
			this.employeeId = employeeId;
		}
		public LocalDate getStartDate() {
			return startDate;
		}
		public void setStartDate(LocalDate startDate) {
			this.startDate = startDate;
		}
		public int getTypeId() {
			return typeId;
		}
		public void setTypeId(int typeId) {
			this.typeId = typeId;
		};
		
		
		
		


}

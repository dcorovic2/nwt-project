package com.outofoffice.leaverequestservice.requestobjects;

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
		/*@Min(value = 1,
	            message = "Department ID must be between 1 to 20")
		@Max(value = 20,
	            message = "Department ID must be between 1 to 20")*/
		private int daysNum;
		
		private int employeeId;
		
		@NotNull(message="Start Date can not be null")
		private OffsetDateTime startDate;
		
		@NotNull(message="Type can not be null")
		private int typeId;
		
		public LeaveRequestRequest() {}

		public LeaveRequestRequest(String comment, int daysNum, int employeeId, OffsetDateTime starDate, OffsetDateTime endDate,
				int statusId, int typeId, int notificationId) {
			super();
			//this.id = id;
			this.comment = comment;
			this.daysNum = daysNum;
			this.employeeId = employeeId;
			//this.endDate = endDate;
			this.startDate = startDate;
			//this.statusId = statusId;
			this.typeId = typeId;
			//this.notificationId = notificationId;
		}
		/*public int getNotificationId() {
			return notificationId;
		}

		public void setNotificationId(int notificationId) {
			this.notificationId = notificationId;
		}*/

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
		public int getEmployeeId() {
			return employeeId;
		}
		public void setEmployeeId(int employeeId) {
			this.employeeId = employeeId;
		}
		/*public OffsetDateTime getEndDate() {
			return endDate;
		}
		public void setEndDate(OffsetDateTime endDate) {
			this.endDate = endDate;
		}*/
		public OffsetDateTime getStartDate() {
			return startDate;
		}
		public void setStartDate(OffsetDateTime startDate) {
			this.startDate = startDate;
		}
		/*public int getStatusId() {
			return statusId;
		}
		public void setStatusId(int statusId) {
			this.statusId = statusId;
		}*/
		public int getTypeId() {
			return typeId;
		}
		public void setTypeId(int typeId) {
			this.typeId = typeId;
		};
		
		
		
		


}

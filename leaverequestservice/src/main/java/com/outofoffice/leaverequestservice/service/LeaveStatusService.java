package com.outofoffice.leaverequestservice.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.outofoffice.leaverequestservice.errorhandling.NotFoundException;
import com.outofoffice.leaverequestservice.model.LeaveRequest;
import com.outofoffice.leaverequestservice.model.LeaveStatus;
import com.outofoffice.leaverequestservice.model.LeaveType;
import com.outofoffice.leaverequestservice.repository.LeaveRequestRepository;
import com.outofoffice.leaverequestservice.repository.LeaveStatusRepository;
import com.outofoffice.leaverequestservice.requestobjects.LeaveRequestRequest;

@Service
public class LeaveStatusService {
	
	private static LeaveStatusRepository leaveStatusRepository;
	
	public LeaveStatusService(LeaveStatusRepository leaveStatusRepository) {
		this.leaveStatusRepository =  leaveStatusRepository;
	}
	
	public static LeaveStatus getById(long id) {
		String id_string = id + "";
			return leaveStatusRepository.findById(id).orElseThrow(()->new NotFoundException(id_string, "Leave Request Status", "ID", ""));
	}
}

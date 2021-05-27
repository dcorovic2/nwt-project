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
import com.outofoffice.leaverequestservice.repository.LeaveTypeRepository;
import com.outofoffice.leaverequestservice.requestobjects.LeaveRequestRequest;

@Service
public class LeaveTypeService {
	
	private static LeaveTypeRepository leaveTypeRepository;
	
	public LeaveTypeService(LeaveTypeRepository leaveTypeRepository) {
		this.leaveTypeRepository =  leaveTypeRepository;
	}
	
	public static LeaveType getById(long id) {
		String id_string = id + "";
			return leaveTypeRepository.findById(id).orElseThrow(()->new NotFoundException(id_string, "Leave Request Type", "ID", ""));
		}
	public static List<LeaveType> getListOfTypes() {
		return leaveTypeRepository.findAll();
	}
}

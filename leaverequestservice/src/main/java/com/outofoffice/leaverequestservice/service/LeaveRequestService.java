package com.outofoffice.leaverequestservice.service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.outofoffice.leaverequestservice.model.LeaveRequest;
import com.outofoffice.leaverequestservice.model.LeaveStatus;
import com.outofoffice.leaverequestservice.model.LeaveType;
import com.outofoffice.leaverequestservice.model.NotificationsType;
import com.outofoffice.leaverequestservice.repository.LeaveRequestRepository;
import com.outofoffice.leaverequestservice.repository.LeaveStatusRepository;
import com.outofoffice.leaverequestservice.service.LeaveTypeService;
import com.outofoffice.leaverequestservice.requestobjects.LeaveRequestRequest;
import com.outofoffice.leaverequestservice.requestobjects.LeaveStatusRequest;
import com.outofoffice.leaverequestservice.responseobjects.LeaveRequestResponse;
import com.outofoffice.leaverequestservice.error.ErrorMessage;
import com.outofoffice.leaverequestservice.errorhandling.NoDataException;
import com.outofoffice.leaverequestservice.errorhandling.NotFoundException;
import com.outofoffice.leaverequestservice.errorhandling.NotSucesfullException;
import com.outofoffice.leaverequestservice.errorhandling.NotValidParamException;

@Service
public class LeaveRequestService {

	private final LeaveRequestRepository leaveRequestRepository;

	public LeaveRequestService(LeaveRequestRepository leaveRequestRepository) {
		this.leaveRequestRepository = leaveRequestRepository;
	}

	public ResponseEntity<?> insertRequest(LeaveRequestRequest req, LeaveRequestResponse response) {
		if(!response.allowance) {
			throw new NotValidParamException("You do not have " + req.getDaysNum() + " days allowed for leave!");
		}
		LocalDate end = req.getStartDate();
		end = end.plusDays(req.getDaysNum());
		List<LeaveRequest> requestList = leaveRequestRepository.findAll();
		List<Long> employeeList = response.getEmployee_ids();
		int x = 1;
		for(Long e : employeeList) {
			for(LeaveRequest r : requestList) {
				if(r.getEmployeeId() == e) {
					if(r.getStartDate().isBefore(req.getStartDate()) && r.getEndDate().isAfter(req.getStartDate())) {
						x++;
						break;
					}
					if(r.getStartDate().isEqual(req.getStartDate())) {
						x++;
						break;
					}
					if(r.getStartDate().isAfter(req.getStartDate()) && r.getEndDate().isBefore(end)) {
						x++;
						break;
					}
				}
			}
		}
	    //System.out.println("Brojac poslije prolaska:" + x);
		if(x > response.getDepartmentallowance()) {
			throw new NotValidParamException("Too many employees from your department took leave at the same time!");
		}
		
		LeaveStatus statusId = LeaveStatusService.getById(1);
		LeaveType typeId = LeaveTypeService.getById(req.getTypeId());
		NotificationsType notification = NotificationsTypeService.getById(1);


		LeaveRequest request = new LeaveRequest(req.getComment(), req.getDaysNum(), req.getEmployeeId(),
				req.getStartDate(), end, typeId, statusId, notification);
		LeaveRequest newreq = leaveRequestRepository.save(request);
		return new ResponseEntity<>(newreq, HttpStatus.OK);
	}

	public ResponseEntity<?> updateRequest(LeaveRequestRequest req, long id) {
		LeaveStatus statusId = LeaveStatusService.getById(1);
		LeaveType typeId = LeaveTypeService.getById(req.getTypeId());
		NotificationsType notification = NotificationsTypeService.getById(1);
		LeaveRequest request;
		String id_request = id + "";
		request = leaveRequestRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(id_request, "Leave Request", "ID", ""));

		LocalDate end = req.getStartDate();
		end = end.plusDays(req.getDaysNum());

		request.setComment(req.getComment());
		request.setDaysNum(req.getDaysNum());
		request.setEmployeeId(req.getEmployeeId());
		request.setEndDate(end);
		request.setStartDate(req.getStartDate());
		request.setLeave_status(statusId);
		request.setLeave_type(typeId);
		request.setNotifications_type(notification);

		LeaveRequest newreq = leaveRequestRepository.save(request);
		return new ResponseEntity<>(newreq, HttpStatus.OK);
	}

	public ResponseEntity<?> updateRequestStatus(LeaveStatusRequest req, long id) {
		LeaveStatus newStatusId = LeaveStatusService.getById(req.getStatusId());
		if (req.getStatusId() != req.getNotificationsId()) {
			throw new NotSucesfullException();
		}
		NotificationsType newNotification = NotificationsTypeService.getById(req.getNotificationsId());

		LeaveRequest request;
		String id_request = id + "";
		request = leaveRequestRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(id_request, "Leave Request", "ID", ""));

		request.setLeave_status(newStatusId);
		request.setNotifications_type(newNotification);

		LeaveRequest newreq = leaveRequestRepository.save(request);
		return new ResponseEntity<>(newreq, HttpStatus.OK);
	}

	public ResponseEntity<?> deleteRequest(Long id) throws Exception {
		LeaveRequest requestForDelete;
		String id_request = id + "";
		requestForDelete = leaveRequestRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(id_request, "Leave Request", "ID", ""));

		leaveRequestRepository.delete(requestForDelete);

		Long deletedRequest = requestForDelete.getId();
		return new ResponseEntity<Long>(deletedRequest, HttpStatus.OK);

	}
	/*
	 * public ResponseEntity<?> getRequestList() { ErrorMessage errorMessage = new
	 * ErrorMessage("Something went wrong!"); try { List<LeaveRequest> requestList =
	 * leaveRequestRepository.findAll(); return new ResponseEntity<>(requestList,
	 * HttpStatus.OK); } catch (Exception e) { return new
	 * ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR); } }
	 */

	public ResponseEntity<?> getStatusRequestList(Long statusId) {
		List<LeaveRequest> requestList;
		if (statusId == 0) {
			requestList = leaveRequestRepository.findAll();
			if(requestList.isEmpty()) {
				throw new NoDataException();
			}
			return new ResponseEntity<>(requestList, HttpStatus.OK);
		}
		LeaveStatus status = LeaveStatusService.getById(statusId);
		requestList = leaveRequestRepository.getAllRequestsForStatus(status);
		if(requestList.isEmpty()) {
			throw new NoDataException();
		}
		return new ResponseEntity<>(requestList, HttpStatus.OK);
	}

	public ResponseEntity<?> getRequestById(long id) {
		LeaveRequest request;
		String id_request = id + "";
		request = leaveRequestRepository.findById(id).orElseThrow(() -> new NotFoundException(id_request, "Leave Request", "ID", ""));
		return new ResponseEntity<>(request, HttpStatus.OK);
	}
}

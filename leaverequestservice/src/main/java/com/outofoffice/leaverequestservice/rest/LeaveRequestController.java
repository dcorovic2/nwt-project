package com.outofoffice.leaverequestservice.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.outofoffice.leaverequestservice.errorhandling.NotValidParamException;
import com.outofoffice.leaverequestservice.errorhandling.RestTemplateException;
import com.outofoffice.leaverequestservice.errorhandling.RestTemplateResponseErrorHandler;
import com.outofoffice.leaverequestservice.model.LeaveRequest;
import com.outofoffice.leaverequestservice.service.LeaveRequestService;
import com.outofoffice.leaverequestservice.requestobjects.LeaveRequestRequest;
import com.outofoffice.leaverequestservice.requestobjects.LeaveStatusRequest;
import com.outofoffice.leaverequestservice.responseobjects.LeaveRequestResponse;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/")

public class LeaveRequestController {
	private final LeaveRequestService LeaveRequestService;
	
	@Autowired
	RestTemplate restTemplate;  

	public LeaveRequestController(LeaveRequestService LeaveRequestService) {
		this.LeaveRequestService = LeaveRequestService;
	}
	
	@PostMapping(value = "/request")
	public ResponseEntity<?> insertRequest(@RequestBody @Valid LeaveRequestRequest requestRequest, Errors errors) {
		if (errors.hasErrors()) {
			throw new NotValidParamException(errors.getFieldError().getDefaultMessage());
		}
		final String uri1 = "http://employee-service/employee/" + requestRequest.getEmployeeId() + "/" + requestRequest.getDaysNum();
		final String uri2 = "http://holiday-service/holiday/" + requestRequest.getStartDate() + "/" + requestRequest.getDaysNum();
		LeaveRequestResponse response1 = new LeaveRequestResponse();
		long response2 = 0;
		
//		response1 = restTemplate.getForObject(uri1, LeaveRequestResponse.class);
//		response2 = restTemplate.getForObject(uri2, long.class);	
		
		try {
			response1 = restTemplate.getForObject(uri1, LeaveRequestResponse.class);
		} catch (IllegalStateException e) {
			throw new RestTemplateException(uri1);
		}
		try {
			response2 = restTemplate.getForObject(uri2, long.class);	
		} catch (IllegalStateException e) {
			throw new RestTemplateException(uri2);
		}  
	    //System.out.println(response2);
		return LeaveRequestService.insertRequest(requestRequest, response1, response2);

	
	}
	
	@PatchMapping(value = "/request")
	public ResponseEntity<?> updateRequest(@RequestBody @Valid LeaveRequestRequest requestRequest, 
			@RequestParam(name = "id", required = true) long id, Errors errors) {
		if (errors.hasErrors()) {
			throw new NotValidParamException(errors.getFieldError().getDefaultMessage());
		}
		final String uri1 = "http://employee-service/employee/" + requestRequest.getEmployeeId() + "/" + requestRequest.getDaysNum();
		final String uri2 = "http://holiday-service/holiday/" + requestRequest.getStartDate() + "/" + requestRequest.getDaysNum();

		LeaveRequestResponse response1 = new LeaveRequestResponse();
	    response1 = restTemplate.getForObject(uri1, LeaveRequestResponse.class);    
	    long response2;
	    response2 = restTemplate.getForObject(uri2, long.class);
		return LeaveRequestService.updateRequest(requestRequest, id, response1, response2);
	}
	@PatchMapping(value = "/request/{id}")
	public ResponseEntity<?> updateRequestStatus(@RequestBody LeaveStatusRequest statusRequest, @PathVariable long id) {		
		return LeaveRequestService.updateRequestStatus(statusRequest, id);
	}

	@ApiOperation(value = "Delete Request", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@DeleteMapping(value = "/request/{id}")
	public ResponseEntity<?> deleteRequest(@PathVariable long id) throws Exception {
		return LeaveRequestService.deleteRequest(id);
	}
	
	@GetMapping("/requests")
	public ResponseEntity<?> getRequestsByStatus(@RequestParam(name = "status_id", required = true) long statusId) {
	    return LeaveRequestService.getStatusRequestList(statusId);
	}
		
	@GetMapping("/request/{id}")
	public ResponseEntity<?> getRequestById(@PathVariable long id){
		return LeaveRequestService.getRequestById(id);
	}
}

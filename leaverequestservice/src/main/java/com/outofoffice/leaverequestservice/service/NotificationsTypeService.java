package com.outofoffice.leaverequestservice.service;

import org.springframework.stereotype.Service;

import com.outofoffice.leaverequestservice.errorhandling.NotFoundException;
import com.outofoffice.leaverequestservice.model.NotificationsType;
import com.outofoffice.leaverequestservice.repository.NotificationsTypeRepository;

@Service
public class NotificationsTypeService {
	
	private static NotificationsTypeRepository notificationsTypeRepository;
	
	public NotificationsTypeService(NotificationsTypeRepository notificationsTypeRepository) {
		this.notificationsTypeRepository =  notificationsTypeRepository;
	}
	
	public static NotificationsType getById(long id) {
		String id_string = id + "";
			return notificationsTypeRepository.findById(id).orElseThrow(()->new NotFoundException(id_string, "Leave Request Type", "ID", ""));
		}


}

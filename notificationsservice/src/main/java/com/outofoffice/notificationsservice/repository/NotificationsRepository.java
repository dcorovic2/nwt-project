package com.outofoffice.notificationsservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.outofoffice.notificationsservice.model.Notification;

@Repository
public interface NotificationsRepository extends CrudRepository<Notification, Long> {
	public List<Notification> findByEmployeesIdAndDismissOrderByCreateDateDesc(long employeeID, int dismiss);
	public List<Notification> findAll();

}

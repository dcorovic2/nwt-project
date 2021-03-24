package com.outofoffice.notificationsservice.repository;

import java.awt.print.Pageable;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.outofoffice.notificationsservice.model.Employee;
import com.outofoffice.notificationsservice.model.Notification;

@Repository
public interface  NotificationsRepository extends CrudRepository<Notification, Long> {
//	private EntityManager entityManager;
//
//	public NotificationsRepository(EntityManager entityManager) {
//		super(Notification.class, entityManager);
//		this.entityManager = entityManager;
//	}
	
	public List<Notification> findById(Long employeeID, PageRequest pageRequest);
	public List<Notification> findAll();
}

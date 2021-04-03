package com.outofoffice.holidayservice.repository;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.outofoffice.holidayservice.model.Notification;

@Repository
public class NotificationRepository extends SimpleJpaRepository<Notification, Long>{
	private EntityManager entityManager;
	

	 public NotificationRepository(EntityManager entityManager) {
		super(Notification.class, entityManager);
		this.entityManager = entityManager;
	}
	
}

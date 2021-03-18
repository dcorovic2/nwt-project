package com.outofoffice.notificationsservice.repository;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.outofoffice.notificationsservice.model.Notification;

@Repository
public class NotificationsRepository extends SimpleJpaRepository<Notification, Long> {
	private EntityManager entityManager;

	public NotificationsRepository(EntityManager entityManager) {
		super(Notification.class, entityManager);
		this.entityManager = entityManager;
	}
}

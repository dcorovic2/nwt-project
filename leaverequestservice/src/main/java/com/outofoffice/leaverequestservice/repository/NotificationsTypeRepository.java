package com.outofoffice.leaverequestservice.repository;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.outofoffice.leaverequestservice.model.NotificationsType;

@Repository
public class NotificationsTypeRepository extends SimpleJpaRepository<NotificationsType, Long> {

	private EntityManager entityManager;

	public NotificationsTypeRepository(EntityManager entityManager) {
		super(NotificationsType.class, entityManager);
		this.entityManager = entityManager;
	}

}

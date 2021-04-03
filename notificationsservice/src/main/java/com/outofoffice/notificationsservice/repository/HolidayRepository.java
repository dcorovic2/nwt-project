package com.outofoffice.notificationsservice.repository;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.outofoffice.notificationsservice.model.Holiday;

@Repository
public class HolidayRepository extends SimpleJpaRepository<Holiday, Long> {
	private EntityManager entityManager;

	public HolidayRepository(EntityManager entityManager) {
		super(Holiday.class, entityManager);
		this.entityManager = entityManager;
	}
}

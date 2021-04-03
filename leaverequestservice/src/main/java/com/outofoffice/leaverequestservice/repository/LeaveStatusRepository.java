package com.outofoffice.leaverequestservice.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.outofoffice.leaverequestservice.model.LeaveRequest;
import com.outofoffice.leaverequestservice.model.LeaveStatus;

@Repository
public class LeaveStatusRepository extends SimpleJpaRepository<LeaveStatus, Long> {

	private EntityManager entityManager;

	public LeaveStatusRepository(EntityManager entityManager) {
		super(LeaveStatus.class, entityManager);
		this.entityManager = entityManager;
	}
}
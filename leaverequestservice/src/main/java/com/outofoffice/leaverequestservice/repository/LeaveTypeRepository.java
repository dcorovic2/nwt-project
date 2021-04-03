package com.outofoffice.leaverequestservice.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.outofoffice.leaverequestservice.model.LeaveRequest;
import com.outofoffice.leaverequestservice.model.LeaveType;

@Repository
public class LeaveTypeRepository extends SimpleJpaRepository<LeaveType, Long> {

	private EntityManager entityManager;

	public LeaveTypeRepository(EntityManager entityManager) {
		super(LeaveType.class, entityManager);
		this.entityManager = entityManager;
	}
	

}

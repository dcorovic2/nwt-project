package com.outofoffice.leaverequestservice.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.outofoffice.leaverequestservice.model.LeaveRequest;
import com.outofoffice.leaverequestservice.model.LeaveStatus;
import com.outofoffice.leaverequestservice.service.LeaveStatusService;

@Repository
public class LeaveRequestRepository extends SimpleJpaRepository<LeaveRequest, Long> {
	 private EntityManager entityManager;

	 public LeaveRequestRepository(EntityManager entityManager) {
			super(LeaveRequest.class, entityManager);
			this.entityManager = entityManager;
		}
		
		public List<LeaveRequest> getAllRequestsForStatus(LeaveStatus status) {
			final List<LeaveRequest> requests = entityManager.createQuery("SELECT req FROM LeaveRequest req"
					+ " WHERE req.leave_status = :id", LeaveRequest.class)
					.setParameter("id", status)
					.getResultList();
			return requests;
			
		}
		
		public List<LeaveRequest> getAllApprovedRequests() {
			LeaveStatus status = LeaveStatusService.getById(2L);
			final List<LeaveRequest> requests = entityManager.createQuery("SELECT req FROM LeaveRequest req"
					+ " WHERE req.leave_status = :id", LeaveRequest.class)
					.setParameter("id", status)
					.getResultList();
			return requests;
			
		}
}

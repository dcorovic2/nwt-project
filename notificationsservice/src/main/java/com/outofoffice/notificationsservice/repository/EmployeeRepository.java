package com.outofoffice.notificationsservice.repository;


import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.outofoffice.notificationsservice.model.Employee;


@Repository
public class EmployeeRepository extends SimpleJpaRepository<Employee, Long> {
	private EntityManager entityManager;

	public EmployeeRepository(EntityManager entityManager) {
		super(Employee.class, entityManager);
		this.entityManager = entityManager;
	}
	
}


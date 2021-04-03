package com.outofoffice.holidayservice.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.outofoffice.holidayservice.model.Employee;

@Repository
public class EmployeeRepository extends SimpleJpaRepository<Employee, Long> {
	 private EntityManager entityManager;

	 public EmployeeRepository(EntityManager entityManager) {
		super(Employee.class, entityManager);
		this.entityManager = entityManager;
	}
		
//	public List<Employee> getAllEmployeesWithGivenName(String name) {
//		final List<Employee> employee = entityManager.createQuery("SELECT emp FROM Employee emp"
//				+ " WHERE emp.firstnameLastName = :firstnameLastName", Employee.class)
//				.setParameter("firstnameLastName", name)
//				.getResultList();
//		return employee;
//	}
}
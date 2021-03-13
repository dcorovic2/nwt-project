package com.outofoffice.outofoffice.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.outofoffice.outofoffice.model.Employee;

@Repository
public class EmployeeRepository extends SimpleJpaRepository<Employee, Long> {
    private final EntityManager entityManager;
	public EmployeeRepository(EntityManager entityManager) {
		super(Employee.class, entityManager);
		this.entityManager = entityManager;
	}
	
	
	public List<Employee> getAllEmployeesWithGivenName(String name) {
		final List<Employee> employees = entityManager.createQuery("SELECT emp FROM Employee emp"
				+ " WHERE emp.name = :name", Employee.class)
				.setParameter("name", name)
				.getResultList();
		return employees;
		
	}

}

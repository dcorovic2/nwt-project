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
	public Employee getEmployeeByEmployeeID(Long employeeID) {
		System.out.println("here p rvi u repo");
		System.out.println(employeeID);
		final Employee employee = entityManager
				.createQuery("SELECT emp FROM Employee emp" + " WHERE emp.id= :employeeID", Employee.class)
				.setParameter("employeeID", employeeID).getSingleResult();
		System.out.println("here");
		System.out.println(employee.getInterni_id());
		return employee;

	}
}


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
	
	
	public Employee getEmployeeByJmbg(String jmbg) {
		final Employee employee = entityManager.createQuery("SELECT emp FROM Employee emp"
				+ " WHERE emp.jmbg = :jmbg", Employee.class)
				.setParameter("jmbg", jmbg)
				.getSingleResult();
		return employee;
		
	}
	
	public List<Long> findByDepartmentId(Long employeeID){
		final List<Long> ids = entityManager.createQuery("SELECT emp.id FROM Employee emp "
				+ "WHERE emp.department.id = "
				+ "(SELECT emp.department.id FROM Employee emp WHERE emp.id = :employeeID)", Long.class)
				.setParameter("employeeID", employeeID)
				.getResultList();
		return ids;
	}
	
	public  Long findAllowanceByEmployeeId(Long employeeID){
		final Long allowance = entityManager.createQuery("SELECT emp.allowance FROM Employee emp"
				+ " WHERE emp.id = :employeeID", Long.class)
				.setParameter("employeeID", employeeID)
				.getSingleResult();
		return allowance;
	}
	
	public Long findDepartmentIdByEmployeeId(Long employeeID){
		final Long depAllowance = entityManager.createQuery("SELECT emp.department.id FROM Employee emp"
				+" WHERE emp.id = :employeeID",Long.class)
				.setParameter("employeeID", employeeID)
				.getSingleResult();
		return depAllowance;
	}
	
	}




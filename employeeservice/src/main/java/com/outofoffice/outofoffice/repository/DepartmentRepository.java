package com.outofoffice.outofoffice.repository;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.outofoffice.outofoffice.model.Department;
import com.outofoffice.outofoffice.model.Employee;

@Repository
public class DepartmentRepository extends SimpleJpaRepository<Department, Long>{
    private final EntityManager entityManager;
      
	public DepartmentRepository(EntityManager entityManager) {
		super(Department.class, entityManager);
		this.entityManager = entityManager;
	}
	
	public Department findByCode(String code) {
		final Department department= entityManager.createQuery("SELECT dep FROM Department dep"
				+ " WHERE dep.code = :code", Department.class)
				.setParameter("code", code)
				.getSingleResult();
		return department;
	}
	
	public Integer findAllowance(Long id) {
		Integer allowance = entityManager.createQuery("SELECT dep.empAllowedNum FROM Department dep"
				+ " WHERE dep.id = :id", Integer.class)
				.setParameter("id", id)
				.getSingleResult();
		return allowance;
	}
	
}

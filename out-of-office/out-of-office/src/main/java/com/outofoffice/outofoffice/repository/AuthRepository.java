package com.outofoffice.outofoffice.repository;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.google.common.base.Optional;
import com.outofoffice.outofoffice.errorhandling.NotFoundException;
import com.outofoffice.outofoffice.model.Auth;
import com.outofoffice.outofoffice.model.Employee;

@Repository
public class AuthRepository  extends SimpleJpaRepository<Auth, Long> {
    private final EntityManager entityManager;
    
	public AuthRepository(EntityManager entityManager) {
		super(Auth.class, entityManager);
		this.entityManager = entityManager;
	}
	
	public Auth findByEmployeeId(Long employee_id) {
		String id_string = employee_id + "";
//		try {
			 Auth authData = entityManager.createQuery("SELECT aut FROM Auth aut"
					+ " WHERE aut.employee.id = :employee_id", Auth.class)
					.setParameter("employee_id", employee_id)
					.getSingleResult();
			return authData;
//		} catch (Exception e) {
//			throw  new NotFoundException(id_string,"Employee", "id", "");
//		}
	}
}

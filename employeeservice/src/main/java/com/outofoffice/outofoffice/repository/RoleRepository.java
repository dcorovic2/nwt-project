package com.outofoffice.outofoffice.repository;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.outofoffice.outofoffice.model.Role;

@Repository
public class RoleRepository extends SimpleJpaRepository<Role, Long> {
    private final EntityManager entityManager;
    
	public RoleRepository(EntityManager entityManager) {
		super(Role.class, entityManager);
		this.entityManager = entityManager;
	}
}

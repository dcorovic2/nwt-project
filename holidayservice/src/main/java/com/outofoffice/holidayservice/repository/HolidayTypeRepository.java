package com.outofoffice.holidayservice.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.outofoffice.holidayservice.model.HolidayType;

@Repository
public class HolidayTypeRepository extends SimpleJpaRepository<HolidayType, Long>{
	 private EntityManager entityManager;

	 public HolidayTypeRepository(EntityManager entityManager) {
		super(HolidayType.class, entityManager);
		this.entityManager = entityManager;
	}
	 
}

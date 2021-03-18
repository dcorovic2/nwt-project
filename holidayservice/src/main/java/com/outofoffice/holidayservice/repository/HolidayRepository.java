package com.outofoffice.holidayservice.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.outofoffice.holidayservice.model.Holiday;

@Repository
public class HolidayRepository extends SimpleJpaRepository<Holiday, Long> {
	 private EntityManager entityManager;

	 public HolidayRepository(EntityManager entityManager) {
			super(Holiday.class, entityManager);
			this.entityManager = entityManager;
		}
		
		public List<Holiday> getAllHolidaysWithGivenName(String name) {
			final List<Holiday> holidays = entityManager.createQuery("SELECT emp FROM Holiday emp"
					+ " WHERE emp.name = :name", Holiday.class)
					.setParameter("name", name)
					.getResultList();
			return holidays;
			
		}
}

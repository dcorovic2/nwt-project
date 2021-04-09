package com.outofoffice.holidayservice.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.outofoffice.holidayservice.model.Holiday;

@Repository
public interface HolidayRepository extends CrudRepository<Holiday, Long> {
//	 private EntityManager entityManager;
//
//	 public HolidayRepository(EntityManager entityManager) {
//		super(Holiday.class, entityManager);
//		this.entityManager = entityManager;
//	}
	
//	 @Query("SELECT holiday FROM Holiday holiday WHERE holiday.holiday_type_id.id = :holidaytypeid")
//	 Holiday findHolidayByHolidayType(@Param("holidaytypeid") Long holidayTypeID);
	 
//	public Holiday findHolidayByHolidayType(Long holidayTypeID) {
//		final Holiday holiday = entityManager.createQuery("SELECT holiday FROM Holiday holiday"
//				+ " WHERE holiday.holiday_type_id.id = :holidaytypeid", Holiday.class)
//				.setParameter("holidaytypeid", holidayTypeID)
//				.getSingleResult();
//		return holiday;
//	}
	 
	 public Holiday findByHolidayTypeId(long holidayTypeID);
	 public List<Holiday> findAll();
	 public Holiday findHolidayByHolidayTypeId(long holidayTypeId);
}

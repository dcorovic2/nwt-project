package com.outofoffice.holidayservice.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.outofoffice.holidayservice.model.Holiday;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {
//	 private EntityManager entityManager;
//
//	 public HolidayRepository(EntityManager entityManager) {
//		super(Holiday.class, entityManager);
//		this.entityManager = entityManager;
//	}
	
	   
	 public Holiday findByHolidayTypeId(long holidayTypeID);
	 public List<Holiday> findAll();
	 public Holiday findHolidayByHolidayTypeId(long holidayTypeId);
	 public List<Holiday> findAllHolidaysByEmployees_id(long employeeId);
	public List<Holiday> findByEmployeesId(Long employeeid);
	public List<Holiday> deleteByEmployees(Long employeeid);

	 
	 
}

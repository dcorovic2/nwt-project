package com.outofoffice.outofoffice.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.outofoffice.outofoffice.model.Employee;
import com.outofoffice.outofoffice.responseobjects.HolidayResponse;

@Repository
@CrossOrigin
public class EmployeeRepository extends SimpleJpaRepository<Employee, Long> {
	private final EntityManager entityManager;

	public EmployeeRepository(EntityManager entityManager) {
		super(Employee.class, entityManager);
		this.entityManager = entityManager;
	}

	public Employee getEmployeeByJmbg(String jmbg) {
		final Employee employee = entityManager
				.createQuery("SELECT emp FROM Employee emp" + " WHERE emp.jmbg = :jmbg", Employee.class)
				.setParameter("jmbg", jmbg).getSingleResult();
		return employee;

	}
	
	public Employee getEmployeeByUsername(String username) {
		final Employee employee = entityManager
				.createQuery("SELECT emp FROM Employee emp" + " WHERE emp.username = :username", Employee.class)
				.setParameter("username", username).getSingleResult();
		return employee;

	}

	public List<Long> findByDepartmentId(Long employeeID) {
		final List<Long> ids = entityManager
				.createQuery("SELECT emp.id FROM Employee emp WHERE emp.department.id = "
						+ "(SELECT emp.department.id FROM Employee emp WHERE emp.id = :employeeID)", Long.class)
				.setParameter("employeeID", employeeID).getResultList();
		return ids;
	}

	public Long findAllowanceByEmployeeId(Long employeeID) {
		final Long allowance = entityManager
				.createQuery("SELECT emp.allowance FROM Employee emp WHERE emp.id = :employeeID", Long.class)
				.setParameter("employeeID", employeeID).getSingleResult();
		return allowance;
	}

	public Long findDepartmentIdByEmployeeId(Long employeeID) {
		final Long depAllowance = entityManager
				.createQuery("SELECT emp.department.id FROM Employee emp WHERE emp.id = :employeeID", Long.class)
				.setParameter("employeeID", employeeID).getSingleResult();
		return depAllowance;
	}

	public List<HolidayResponse> findNamesAndIds() {
		final List<Employee> employees = entityManager.createQuery("SELECT emp FROM Employee emp ", Employee.class)
				.getResultList();
		final List<HolidayResponse> names = new ArrayList<>();
		employees.forEach(employee -> {
			names.add(new HolidayResponse(employee.getId(), employee.getFirstnameLastName()));
		});
		return names;
	}

}

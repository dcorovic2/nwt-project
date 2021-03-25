package com.outofoffice.outofoffice.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.outofoffice.outofoffice.errorhandling.NoDataException;
import com.outofoffice.outofoffice.errorhandling.NotFoundException;
import com.outofoffice.outofoffice.errorhandling.NotSucesfullException;
import com.outofoffice.outofoffice.model.Department;
import com.outofoffice.outofoffice.repository.DepartmentRepository;

@Service
public class DepartmentService {
	private final DepartmentRepository departmentRepository;

	public DepartmentService(DepartmentRepository departmentRepository) {
		super();
		this.departmentRepository = departmentRepository;
	}

	public Department getById(Long id) {
		String id_string = id + "";
		return departmentRepository.findById(id).orElseThrow(() -> new NotFoundException(id_string, "Department", "id", ""));
	}

	public Department getByCode(String code) {
		try {
			Department department = departmentRepository.findByCode(code);
			if(department == null) throw new NotFoundException("", "Department", "code", code);
			return department;
		} catch (Exception e) {
			throw new NotSucesfullException();
		}
		
	}
	public List<Department> insertBulkDepartment(List<Department> departments) {
try {
			List<Department> insertedDepartments = new ArrayList<Department>();
		departments.forEach(department -> {
			insertedDepartments.add(insertDepartment(department));
		});

		return insertedDepartments;
} catch (Exception e) {
	throw new NotSucesfullException();
}
	}

	public Department insertDepartment(Department department) {
		try {
			return departmentRepository.save(department);
		} catch (Exception e) {
			throw new NotSucesfullException();
		}
	
	}

	public List<Department> getAll() {

			List<Department> all_departments = new ArrayList<Department>();
			all_departments = departmentRepository.findAll();
			if(all_departments.isEmpty()) throw new NoDataException();
	try {
			return all_departments;
		} catch (Exception e) {
			throw new NotSucesfullException();
		}
	}
	public Long deleteDepartment(Long department_id) {
		String id_string = department_id + "";
		try {
			Department departmentForDelete = departmentRepository.findById(department_id).get();
			if(departmentForDelete==null) throw new NotFoundException(id_string, "Department", "id", "");
			departmentRepository.delete(departmentForDelete);
			return departmentForDelete.getId();
		} catch (Exception e) {
			throw new NotSucesfullException();
		}		
	}
	public Department updateDepartment(Department departmentRequest, Long id) {
	   String id_string = id +"";
	   Department updated_department = departmentRepository.findById(id).orElseThrow(() -> new NotFoundException(id_string, "Department", "id", ""));
		   try {
			   updated_department.setCode(departmentRequest.getCode());
			   updated_department.setDisplayName(departmentRequest.getDisplayName());
			   updated_department.setEmpAllowedNum(departmentRequest.getEmpAllowedNum());
			   updated_department.setName(departmentRequest.getName());
			   return departmentRepository.save(updated_department);
		} catch (Exception e) {
			throw new NotSucesfullException();
		}

	
	}
	
	
}

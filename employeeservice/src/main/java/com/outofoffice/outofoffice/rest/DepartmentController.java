package com.outofoffice.outofoffice.rest;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.outofoffice.outofoffice.model.Department;
import com.outofoffice.outofoffice.service.DepartmentService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/")
public class DepartmentController {
    private final DepartmentService departmentService;
	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	@ApiOperation(value = "Insert multiple departments", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(value = "/bulkDepartmentCreate")
	public ResponseEntity<List<Department>> bulkInsert(@RequestBody List<Department> departments) {
	List<Department> insertedDepartments = departmentService.insertBulkDepartment(departments);
	return new ResponseEntity<List<Department>>( insertedDepartments, HttpStatus.CREATED);
		
	}
	@ApiOperation(value = "Insert One Department", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(value = "/department")
	public ResponseEntity<Department> insertDepartment(@RequestBody Department department) {
	Department insertedDepartment = departmentService.insertDepartment(department);
	return new ResponseEntity<Department>(insertedDepartment, HttpStatus.CREATED);
	}
	@ApiOperation(value = "Get all departments", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value="/getAllDepartments")
	public ResponseEntity<List<Department>> getDepartments() {
	List<Department> allDepartments = departmentService.getAll();
	return new ResponseEntity<List<Department>>(allDepartments , HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Delete Department", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@DeleteMapping(value = "/deleteDepartment/{id}")
	public ResponseEntity<?> deleteDepartment(@PathVariable(value = "id") Long department_id) {
			Long deletedDepartment = departmentService.deleteDepartment(department_id);
			return new ResponseEntity<Long>(deletedDepartment, HttpStatus.OK);
	}
	@ApiOperation(value="Change Department data based on id",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	@PutMapping(value="/changeDepartment/{id}")
	public ResponseEntity<Department> updateDepartmentData(@PathVariable(value = "id") Long id,  @RequestBody Department departmentRequest){
		Department department = departmentService.updateDepartment(departmentRequest, id);
		return new ResponseEntity<Department>(department, HttpStatus.OK);
	}
}

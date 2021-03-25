package com.outofoffice.outofoffice.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.outofoffice.outofoffice.model.Auth;
import com.outofoffice.outofoffice.model.Employee;
import com.outofoffice.outofoffice.requestobjects.EmployeeRequest;
import com.outofoffice.outofoffice.requestobjects.LoginRequest;
import com.outofoffice.outofoffice.service.LoginService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/")
public class LoginController {
	private final LoginService loginService;
	public LoginController(LoginService loginService) {
		super();
		this.loginService = loginService;
	}
	@ApiOperation(value = "Insert New Auth Data", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(value = "/login")
	public ResponseEntity<Auth> insertLogin(@RequestBody LoginRequest login) {
	Auth insertedLogin = loginService.insertLogin(login);
	return new ResponseEntity<Auth>(insertedLogin, HttpStatus.CREATED);
	}
	@ApiOperation(value = "Get All Auth Data", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(value="/getAuthData")
	public ResponseEntity<List<Auth>> getAuthData() {
	List<Auth> allDepartments = loginService.getAll();
	return new ResponseEntity<List<Auth>>(allDepartments , HttpStatus.CREATED);
	}
	@ApiOperation(value = "Delete Auth Data", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@DeleteMapping(value = "/deleteAuth")
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> deleteAuth(@RequestParam(name = "employee_id", required = true) Long employee_id)
			throws RuntimeException {
		try {
			Long deletedAuth = loginService.deleteAuth(employee_id);
			return new ResponseEntity<Long>(deletedAuth, HttpStatus.OK);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

	}
	@ApiOperation(value="Change Employee auth data based on id",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	@PutMapping(value="/changeAuth/{id}")
	public ResponseEntity<Auth> updateAuthData(@PathVariable(value = "id") Long id,  @RequestBody LoginRequest authRequest){
		Auth auth = loginService.updateAuth(authRequest, id);
		return new ResponseEntity<Auth>(auth, HttpStatus.OK);
	}

}

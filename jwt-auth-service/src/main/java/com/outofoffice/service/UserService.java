package com.outofoffice.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.outofoffice.dto.PasswordDTO;
import com.outofoffice.exception.CustomException;
import com.outofoffice.model.User;
import com.outofoffice.repository.UserRepository;
import com.outofoffice.security.JwtTokenProvider;
import java.util.List;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	public String signin(String username, String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
		} catch (AuthenticationException e) {
			throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	public User checkPassword(String password, String username) {
		try {
			User user = userRepository.findByUsername(username);
			if (!encoder.matches(password, user.getPassword())) {
				return null;
			} else {
				return user;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public ResponseEntity<?> updatePassword(PasswordDTO password, Long employeeId) {
		try {
			User user = userRepository.findById(employeeId);
			System.out.print(password.getPassword());
			user.setPassword(passwordEncoder.encode(password.getPassword()));
			User updateduser = userRepository.save(user);
			return new ResponseEntity<>(updateduser, HttpStatus.OK);
		} catch (Exception e) {
			return null;
		}
	}

	public String signup(User user) {
		System.out.println("Udje u signup metodu");
		if (!userRepository.existsByUsername(user.getUsername())) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(user);
			return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
		} else {
			throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	public void delete(String username) {
		userRepository.deleteByUsername(username);
	}

	public User search(String username) {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
		}
		return user;
	}

	public User whoami(HttpServletRequest req) {
		return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
	}

	public String refresh(String username) {
		return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
	}

}

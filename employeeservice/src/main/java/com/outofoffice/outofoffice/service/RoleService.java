package com.outofoffice.outofoffice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.outofoffice.outofoffice.errorhandling.NotFoundException;
import com.outofoffice.outofoffice.errorhandling.NotSucesfullException;
import com.outofoffice.outofoffice.model.Role;
import com.outofoffice.outofoffice.repository.RoleRepository;

@Service
public class RoleService {
	private final RoleRepository roleRepository;

	public RoleService(RoleRepository roleRepository) {
		super();
		this.roleRepository = roleRepository;
	}

	public Role getById(Long id) {
		String id_string = id + "";
		return roleRepository.findById(id).orElseThrow(() -> new NotFoundException(id_string, "Role", "id", ""));
	}

	public List<Role> insertBulkRole(List<Role> roles) {
		try {
			List<Role> insertedRoles = new ArrayList<Role>();
			roles.forEach(role -> {
				insertedRoles.add(insertRole(role));
			});

			return insertedRoles;
		} catch (Exception e) {
			throw new NotSucesfullException();
		}

	}

	public Role insertRole(Role role) {
		try {
			return roleRepository.save(role);
		} catch (Exception e) {
			throw new NotSucesfullException();
		}

	}

	public List<Role> getAll() {
		return roleRepository.findAll();
	}
}

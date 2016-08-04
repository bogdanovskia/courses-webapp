package com.courses.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courses.model.UserRole;
import com.courses.persistence.UserRoleRepository;
import com.courses.service.UserRoleService;

@Service
public class UserRoleServiceImplementation implements UserRoleService {

	@Autowired
	UserRoleRepository userRoleRepository;

	public UserRole save(UserRole s) {
		return userRoleRepository.save(s);
	}

	public int delete(UserRole s) {
		return userRoleRepository.deleteById(s.getId());
	}

	public UserRole getById(long id) {
		return userRoleRepository.findById(id);
	}

	public List<UserRole> getAll() {
		return userRoleRepository.findAll();
	}

}

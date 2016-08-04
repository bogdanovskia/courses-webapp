package com.courses.persistence;

import java.util.List;

import com.courses.model.UserRole;

public interface UserRoleRepository {
	UserRole findById(long id);

	List<UserRole> findAll();

	UserRole save(UserRole u);

	int deleteById(long id);
}

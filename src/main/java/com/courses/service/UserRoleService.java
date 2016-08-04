package com.courses.service;

import java.util.List;

import com.courses.model.UserRole;

public interface UserRoleService {
	public UserRole save(UserRole s);

	public int delete(UserRole s);

	public UserRole getById(long id);

	public List<UserRole> getAll();

}

package com.courses.service;

import java.util.List;

import com.courses.model.User;

public interface UserService {
	public User save(User s);
	
	public int delete(User s);
	
	public User getById(long id);
	
	public List<User> getAll();

	public boolean isValidUser(String username, String password);

	public User getUserByUsername(String username);
}

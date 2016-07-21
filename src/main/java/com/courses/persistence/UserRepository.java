package com.courses.persistence;

import java.util.List;

import com.courses.model.User;

public interface UserRepository<T extends User> {
	public User save(User s);

	public int delete(User s);

	public User getById(long id);

	public List<User> getAll();

	public boolean isValidUser(String username, String password);

	public T getUserByUsername(String username);
}

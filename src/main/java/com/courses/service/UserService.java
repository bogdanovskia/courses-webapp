package com.courses.service;

import java.util.List;
import java.util.Set;

import com.courses.model.Course;
import com.courses.model.User;

public interface UserService<T extends User> {
	public T save(T s);

	public int delete(User s);

	public User getById(long id);

	public List<User> getAll();

	public boolean isValidUser(String username, String password);

	public User getUserByUsername(String username);

	public Set<Course> getCourses(User u);
}

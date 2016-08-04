package com.courses.persistence;

import java.util.List;
import java.util.Set;

import com.courses.model.Course;
import com.courses.model.User;
import com.courses.model.UserRole;

public interface UserRepository<T extends User> {
	public User save(User s);

	public int delete(User s);

	public User getById(long id);

	public List<User> getAll();

	public boolean isValidUser(String username, String password);

	public T getUserByUsername(String username);

	public Set<Course> getCourses(User u);

	public Set<UserRole> getRoles(com.courses.model.User user);
}

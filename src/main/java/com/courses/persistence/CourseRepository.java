package com.courses.persistence;

import java.util.List;

import com.courses.model.Course;

public interface CourseRepository {
	public Course save(Course s);

	public int delete(Course s);

	public Course getById(long id);

	public List<Course> getAll();

	public Course getByName(String courseName);
}

package com.courses.persistence;

import java.util.List;
import java.util.Set;

import com.courses.model.Course;
import com.courses.model.Student;

public interface StudentRepository {
	public Student save(Student s);

	public int delete(Student s);

	public Student getById(long id);

	public List<Student> getAll();

	public Set<Course> getCourses(long id);
}

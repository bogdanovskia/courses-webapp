package com.courses.persistence;

import java.util.List;

import com.courses.model.Student;

public interface StudentRepository {
	public Student save(Student s);

	public int delete(Student s);

	public Student getById(long id);

	public List<Student> getAll();
}

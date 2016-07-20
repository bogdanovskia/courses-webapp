package com.courses.service;
import java.util.*;

import com.courses.model.Course;
import com.courses.model.Student;

public interface StudentService {
	public Student save(Student s);
	
	public int delete(Student s);
	
	public Student getById(long id);
	
	public List<Student> getAll();

	public Set<Course> getCourses(long id);
}

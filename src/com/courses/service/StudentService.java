package com.courses.service;
import java.util.*;
import com.courses.model.Student;

public interface StudentService {
	public Student save(Student s);
	
	public int delete(Student s);
	
	public Student getById(long id);
	
	public List<Student> getAll();
}

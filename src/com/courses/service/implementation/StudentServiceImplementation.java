package com.courses.service.implementation;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courses.model.Course;
import com.courses.model.Student;
import com.courses.persistence.StudentRepository;
import com.courses.service.StudentService;

@Service("studentBo")
public class StudentServiceImplementation implements StudentService{
	
	@Autowired
	StudentRepository studentRepository;

	@Override
	public Student save(Student s) {
		return studentRepository.save(s);
	}

	@Override
	public int delete(Student s) {
		return studentRepository.delete(s);
	}

	@Override
	public Student getById(long id) {
		return studentRepository.getById(id);
	}

	@Override
	public List<Student> getAll() {
		return studentRepository.getAll();
	}

	@Override
	public Set<Course> getCourses(long id) {
		return studentRepository.getCourses(id);
	}
	
	
	
}

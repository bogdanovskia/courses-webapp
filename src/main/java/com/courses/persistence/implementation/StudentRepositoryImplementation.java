package com.courses.persistence.implementation;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.courses.model.Course;
import com.courses.model.Student;
import com.courses.persistence.BaseRepository;
import com.courses.persistence.StudentRepository;

@Repository
public class StudentRepositoryImplementation implements StudentRepository {

	@Autowired
	BaseRepository baseRepository;

	public Student save(Student s) {
		return baseRepository.saveOrUpdate(s);
	}

	public int delete(Student s) {
		return baseRepository.delete(Student.class, s.getId());
	}

	public Student getById(long id) {
		return baseRepository.getById(Student.class, id);
	}

	public List<Student> getAll() {
		return baseRepository.find(Student.class, null);
	}

	public Set<Course> getCourses(long id) {
		return baseRepository.getCoursesByStudent(id);
	}

}

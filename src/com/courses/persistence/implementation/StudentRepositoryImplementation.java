package com.courses.persistence.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.courses.model.Student;
import com.courses.persistence.BaseRepository;
import com.courses.persistence.StudentRepository;

@Repository
public class StudentRepositoryImplementation implements StudentRepository{

	@Autowired
	BaseRepository baseRepository;

	@Override
	public Student save(Student s) {
		return baseRepository.saveOrUpdate(s);
	}

	@Override
	public int delete(Student s) {
		return baseRepository.delete(Student.class, s.getId());
	}

	@Override
	public Student getById(long id) {
		return baseRepository.getById(Student.class, id);
	}

	@Override
	public List<Student> getAll() {
		return baseRepository.find(Student.class, null);
	}
	
}

package com.courses.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courses.model.Course;
import com.courses.persistence.CourseRepository;
import com.courses.service.CourseService;

@Service("courseBo")
public class CourseServiceImplementation implements  CourseService{
	
	@Autowired
	CourseRepository courseRepository;

	@Override
	public Course save(Course s) {
		return courseRepository.save(s);
	}

	@Override
	public int delete(Course s) {
		return courseRepository.delete(s);
	}

	@Override
	public Course getById(long id) {
		return courseRepository.getById(id);
	}

	@Override
	public List<Course> getAll() {
		return courseRepository.getAll();
	}

	@Override
	public Course getByName(String courseName) {
		return courseRepository.getByName(courseName);
	}

}

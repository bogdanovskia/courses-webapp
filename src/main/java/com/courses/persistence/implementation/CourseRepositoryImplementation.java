package com.courses.persistence.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.courses.model.Course;
import com.courses.model.Lesson;
import com.courses.persistence.BaseRepository;
import com.courses.persistence.CourseRepository;

@Repository
public class CourseRepositoryImplementation implements CourseRepository {

	@Autowired
	BaseRepository baseRepository;

	public Course save(Course s) {
		return baseRepository.saveOrUpdate(s);
	}

	public int delete(Course s) {
		return baseRepository.delete(Course.class, s.getId());
	}

	public Course getById(long id) {
		return baseRepository.getById(Course.class, id);
	}

	public List<Course> getAll() {
		return baseRepository.find(Course.class, null);
	}

	public Course getByName(String courseName) {
		return baseRepository.getCourseByName(courseName);
	}

	public List<Lesson> getLessonsByCourse(long id) {
		return baseRepository.getLessonsByCourse(id);
	}

	// @Override
	// public Course save(Course s) {
	// return baseRepository.saveOrUpdate(s);
	// }
	//
	// @Override
	// public int delete(Course s) {
	// return baseRepository.delete(Course.class, s.getId());
	// }
	//
	// @Override
	// public Course getById(long id) {
	// return baseRepository.getById(Course.class, id);
	// }
	//
	// @Override
	// public List<Course> getAll() {
	// return baseRepository.find(Course.class, null);
	// }
	//
	// @Override
	// public Course getByName(String courseName) {
	// return baseRepository.getCourseByName(courseName);
	// }

}

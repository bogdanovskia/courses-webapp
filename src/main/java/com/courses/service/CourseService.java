package com.courses.service;

import java.util.List;

import com.courses.model.Course;
import com.courses.model.Lesson;

public interface CourseService {

	public Course save(Course s);

	public int delete(Course s);

	public Course getById(long id);

	public List<Course> getAll();

	public Course getByName(String courseName);

	public List<Lesson> getLessonsByCourse(long id);
}

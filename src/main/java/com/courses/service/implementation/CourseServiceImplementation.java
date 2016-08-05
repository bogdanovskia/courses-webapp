package com.courses.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courses.model.Course;
import com.courses.model.JoinedStudentCourse;
import com.courses.model.Lesson;
import com.courses.persistence.CourseRepository;
import com.courses.service.CourseService;
import com.courses.service.JoinedStudentCourseService;
import com.courses.service.LessonDocumentService;
import com.courses.service.LessonService;
import com.courses.service.UserService;

@Service("courseBo")
public class CourseServiceImplementation implements CourseService {

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	UserService userService;

	@Autowired
	LessonService lessonService;

	@Autowired
	LessonDocumentService lessonDocumentService;

	@Autowired
	JoinedStudentCourseService joinedStudentCourseService;

	public Course save(Course s) {
		return courseRepository.save(s);
	}

	public int delete(Course course) {
		for (Lesson l : course.getLessons()) {
			lessonService.delete(l);
		}

		List<JoinedStudentCourse> joined = joinedStudentCourseService.getByCourse(course);
		for (JoinedStudentCourse j : joined) {
			joinedStudentCourseService.delete(j);
		}

		return courseRepository.delete(course);
	}

	public Course getById(long id) {
		return courseRepository.getById(id);
	}

	public List<Course> getAll() {
		return courseRepository.getAll();
	}

	public Course getByName(String courseName) {
		return courseRepository.getByName(courseName);
	}

	public List<Lesson> getLessonsByCourse(long id) {
		return courseRepository.getLessonsByCourse(id);
	}

}

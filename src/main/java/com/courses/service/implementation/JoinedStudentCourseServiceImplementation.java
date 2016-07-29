package com.courses.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courses.model.Course;
import com.courses.model.JoinedStudentCourse;
import com.courses.model.Student;
import com.courses.persistence.JoinedStudentCourseRepository;
import com.courses.service.JoinedStudentCourseService;

@Service
public class JoinedStudentCourseServiceImplementation implements JoinedStudentCourseService {

	@Autowired
	JoinedStudentCourseRepository joinedStudentCourseRepository;

	public JoinedStudentCourse save(JoinedStudentCourse s) {
		return joinedStudentCourseRepository.save(s);
	}

	public int delete(JoinedStudentCourse s) {
		return joinedStudentCourseRepository.delete(s);
	}

	public JoinedStudentCourse getById(long id) {
		return joinedStudentCourseRepository.getById(id);
	}

	public List<JoinedStudentCourse> getAll() {
		return joinedStudentCourseRepository.getAll();
	}

	public JoinedStudentCourse getByStudentCourse(Student student, Course course) throws Exception {
		return joinedStudentCourseRepository.getByStudentCourse(student, course);
	}

	public List<JoinedStudentCourse> getByStudent(Student user) {
		return joinedStudentCourseRepository.getByStudent(user);
	}

	public List<JoinedStudentCourse> getByCourse(Course course) {
		return joinedStudentCourseRepository.getByCourse(course);
	}

}

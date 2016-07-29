package com.courses.persistence.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.courses.model.Course;
import com.courses.model.JoinedStudentCourse;
import com.courses.model.Student;
import com.courses.persistence.BaseRepository;
import com.courses.persistence.JoinedStudentCourseRepository;

@Repository
public class JoinedStudentCourseRepositoryImplementation implements JoinedStudentCourseRepository {

	@Autowired
	BaseRepository baseRepository;

	public JoinedStudentCourse save(JoinedStudentCourse s) {
		return baseRepository.saveOrUpdate(s);
	}

	public int delete(JoinedStudentCourse s) {
		return baseRepository.delete(JoinedStudentCourse.class, s.getId());
	}

	public JoinedStudentCourse getById(long id) {
		return baseRepository.getById(JoinedStudentCourse.class, id);
	}

	public List<JoinedStudentCourse> getAll() {
		return baseRepository.find(JoinedStudentCourse.class, null);
	}

	public JoinedStudentCourse getByStudentCourse(Student student, Course course) {
		return baseRepository.getJoinedByStudentCourse(student, course);
	}

	public List<JoinedStudentCourse> getByStudent(Student user) {
		return baseRepository.geJoinedByCourses(user);
	}

	public List<JoinedStudentCourse> getByCourse(Course course) {
		return baseRepository.getByCourse(course);
	}

}

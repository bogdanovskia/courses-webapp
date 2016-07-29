package com.courses.persistence;

import java.util.List;

import com.courses.model.Course;
import com.courses.model.JoinedStudentCourse;
import com.courses.model.Student;

public interface JoinedStudentCourseRepository {
	public JoinedStudentCourse save(JoinedStudentCourse s);

	public int delete(JoinedStudentCourse s);

	public JoinedStudentCourse getById(long id);

	public List<JoinedStudentCourse> getAll();

	public JoinedStudentCourse getByStudentCourse(Student student, Course course);

	public List<JoinedStudentCourse> getByStudent(Student user);

	public List<JoinedStudentCourse> getByCourse(Course course);

}

package com.courses.persistence;

import java.util.List;

import com.courses.model.JoinedStudentLesson;

public interface JoinedStudentLessonRepository {
	public JoinedStudentLesson save(JoinedStudentLesson s);

	public int delete(JoinedStudentLesson s);

	public JoinedStudentLesson getById(long id);

	public List<JoinedStudentLesson> getAll();

	public JoinedStudentLesson getByIdAndStudent(long lid, long id);

	public List<JoinedStudentLesson> getByCourseAndStudent(long id, long id2);
}

package com.courses.persistence.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.courses.model.JoinedStudentLesson;
import com.courses.persistence.BaseRepository;
import com.courses.persistence.JoinedStudentLessonRepository;

@Repository
public class JoinedStudentLessonRepositoryImplementation implements JoinedStudentLessonRepository {

	@Autowired
	BaseRepository baseRepository;

	public JoinedStudentLesson save(JoinedStudentLesson s) {
		return baseRepository.saveOrUpdate(s);
	}

	public int delete(JoinedStudentLesson s) {
		return baseRepository.delete(JoinedStudentLesson.class, s.getId());
	}

	public JoinedStudentLesson getById(long id) {
		return baseRepository.getById(JoinedStudentLesson.class, id);
	}

	public List<JoinedStudentLesson> getAll() {
		return baseRepository.find(JoinedStudentLesson.class, null);
	}

	public JoinedStudentLesson getByIdAndStudent(long lid, long id) {
		return baseRepository.getByIdAndStudent(lid, id);
	}

	public List<JoinedStudentLesson> getByCourseAndStudent(long id, long id2) {
		return baseRepository.getByCourseAndStudent(id, id2);
	}

}

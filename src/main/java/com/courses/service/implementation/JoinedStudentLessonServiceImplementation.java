package com.courses.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courses.model.JoinedStudentLesson;
import com.courses.persistence.JoinedStudentLessonRepository;
import com.courses.service.JoinedStudentLessonService;

@Service
public class JoinedStudentLessonServiceImplementation implements JoinedStudentLessonService {

	@Autowired
	JoinedStudentLessonRepository joinedStudentLessonRepository;

	public JoinedStudentLesson save(JoinedStudentLesson s) {
		return joinedStudentLessonRepository.save(s);
	}

	public int delete(JoinedStudentLesson s) {
		return joinedStudentLessonRepository.delete(s);
	}

	public JoinedStudentLesson getById(long id) {
		return joinedStudentLessonRepository.getById(id);
	}

	public List<JoinedStudentLesson> getAll() {
		return joinedStudentLessonRepository.getAll();
	}

	public JoinedStudentLesson getByIdAndStudent(long lid, long id) {
		return joinedStudentLessonRepository.getByIdAndStudent(lid, id);
	}

	public List<JoinedStudentLesson> getByCourseAndStudent(long id, long id2) {
		return joinedStudentLessonRepository.getByCourseAndStudent(id, id2);
	}

}

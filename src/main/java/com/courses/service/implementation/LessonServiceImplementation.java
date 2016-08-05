package com.courses.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courses.model.Course;
import com.courses.model.JoinedStudentLesson;
import com.courses.model.Lesson;
import com.courses.model.LessonDocument;
import com.courses.persistence.LessonRepository;
import com.courses.service.JoinedStudentLessonService;
import com.courses.service.LessonDocumentService;
import com.courses.service.LessonService;

@Service
public class LessonServiceImplementation implements LessonService {

	@Autowired
	LessonRepository lessonRepository;

	@Autowired
	LessonDocumentService lessonDocumentService;

	@Autowired
	JoinedStudentLessonService joinedStudentLessonService;

	public Lesson save(Lesson s) {
		return lessonRepository.save(s);
	}

	public int delete(Lesson s) {
		for (LessonDocument l : s.getLessonDocuments()) {
			lessonDocumentService.deleteById(l.getId());
		}
		for (JoinedStudentLesson j : joinedStudentLessonService.getAll()) {
			if (j.getLesson().equals(s)) {
				joinedStudentLessonService.delete(j);
			}
		}
		return lessonRepository.delete(s);
	}

	public Lesson getById(long id) {
		return lessonRepository.getById(id);
	}

	public List<Lesson> getAll() {
		return lessonRepository.getAll();
	}

	public Lesson getByName(String title) {
		return lessonRepository.getByName(title);
	}

	public void deleteAllOfCourse(Course course) {
		List<Lesson> lessons = lessonRepository.getAll();
		for (Lesson lesson : lessons) {
			if (lesson.getCourse().equals(course)) {
				lessonRepository.delete(lesson);
			}
		}

	}

}

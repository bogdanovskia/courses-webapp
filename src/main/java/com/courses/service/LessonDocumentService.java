package com.courses.service;

import java.util.List;

import com.courses.model.Lesson;
import com.courses.model.LessonDocument;

public interface LessonDocumentService {
	LessonDocument findById(long id);

	List<LessonDocument> findAll();

	List<LessonDocument> findAllByLessonId(long id);

	LessonDocument saveDocument(LessonDocument document);

	int deleteById(long id);

	boolean containsName(String originalFilename, List<LessonDocument> lessonDocuments);

	void deleteAllOfLesson(Lesson l);

	List<LessonDocument> getByLessonId(long id);

}

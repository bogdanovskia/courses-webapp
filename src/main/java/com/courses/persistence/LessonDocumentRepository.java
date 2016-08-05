package com.courses.persistence;

import java.util.List;

import com.courses.model.LessonDocument;

public interface LessonDocumentRepository {
	LessonDocument findById(long id);

	List<LessonDocument> findAll();

	List<LessonDocument> findAllByLessonId(long id);

	LessonDocument saveDocument(LessonDocument document);

	int deleteById(long id);

	List<LessonDocument> getByLessonId(long id);

}

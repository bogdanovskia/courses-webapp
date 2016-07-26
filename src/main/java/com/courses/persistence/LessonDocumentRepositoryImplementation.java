package com.courses.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.courses.model.LessonDocument;

@Repository
public class LessonDocumentRepositoryImplementation implements LessonDocumentRepository {

	@Autowired
	BaseRepository baseRepository;

	public LessonDocument findById(long id) {
		return baseRepository.getById(LessonDocument.class, id);
	}

	public List<LessonDocument> findAll() {
		return baseRepository.find(LessonDocument.class, null);
	}

	public LessonDocument saveDocument(LessonDocument document) {
		return baseRepository.saveOrUpdate(document);
	}

	public int deleteById(long id) {
		return baseRepository.delete(LessonDocument.class, id);
	}

	public List<LessonDocument> findAllByLessonId(long id) {
		return baseRepository.getDocumentsByLesson(id);
	}

}

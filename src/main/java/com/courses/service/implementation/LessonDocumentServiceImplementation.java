package com.courses.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courses.model.LessonDocument;
import com.courses.persistence.LessonDocumentRepository;
import com.courses.service.LessonDocumentService;

@Service
public class LessonDocumentServiceImplementation implements LessonDocumentService {

	@Autowired
	LessonDocumentRepository lessonDocumentRepository;

	public LessonDocument findById(long id) {
		return lessonDocumentRepository.findById(id);
	}

	public List<LessonDocument> findAll() {
		return lessonDocumentRepository.findAll();
	}

	public List<LessonDocument> findAllByLessonId(long id) {
		return lessonDocumentRepository.findAllByLessonId(id);
	}

	public LessonDocument saveDocument(LessonDocument document) {
		return lessonDocumentRepository.saveDocument(document);
	}

	public int deleteById(long id) {
		return lessonDocumentRepository.deleteById(id);
	}

	public boolean containsName(String originalFilename, List<LessonDocument> lessonDocuments) {
		for (LessonDocument l : lessonDocuments) {
			if (l.getName().equals(originalFilename)) {
				return true;
			}
		}
		return false;
	}

}

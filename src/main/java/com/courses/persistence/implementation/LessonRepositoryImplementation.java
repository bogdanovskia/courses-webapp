package com.courses.persistence.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.courses.model.Lesson;
import com.courses.persistence.BaseRepository;
import com.courses.persistence.LessonRepository;

@Repository
public class LessonRepositoryImplementation implements LessonRepository {

	@Autowired
	BaseRepository baseRepository;

	public Lesson save(Lesson s) {
		return baseRepository.saveOrUpdate(s);
	}

	public int delete(Lesson s) {
		return baseRepository.delete(Lesson.class, s.getId());
	}

	public Lesson getById(long id) {
		return baseRepository.getById(Lesson.class, id);
	}

	public List<Lesson> getAll() {
		return baseRepository.find(Lesson.class, null);
	}

	public Lesson getByName(String title) {
		return baseRepository.getLessonByName(title);
	}

}

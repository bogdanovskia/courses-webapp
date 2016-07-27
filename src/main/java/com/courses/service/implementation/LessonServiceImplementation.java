package com.courses.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courses.model.Lesson;
import com.courses.persistence.LessonRepository;
import com.courses.service.LessonService;

@Service
public class LessonServiceImplementation implements LessonService {

	@Autowired
	LessonRepository lessonRepository;

	public Lesson save(Lesson s) {
		return lessonRepository.save(s);
	}

	public int delete(Lesson s) {
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

}

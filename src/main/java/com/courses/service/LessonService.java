package com.courses.service;

import java.util.List;

import com.courses.model.Lesson;

public interface LessonService {

	public Lesson save(Lesson s);

	public int delete(Lesson s);

	public Lesson getById(long id);

	public List<Lesson> getAll();

}

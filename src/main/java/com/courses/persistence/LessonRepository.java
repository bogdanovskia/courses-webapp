package com.courses.persistence;

import java.util.List;

import com.courses.model.Lesson;

public interface LessonRepository {
	public Lesson save(Lesson s);

	public int delete(Lesson s);

	public Lesson getById(long id);

	public List<Lesson> getAll();
}

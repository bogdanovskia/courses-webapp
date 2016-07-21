package com.courses.persistence;

import java.util.List;
import java.util.Set;

import com.courses.model.Course;
import com.courses.model.Professor;

public interface ProfessorRepository {
	public Professor save(Professor s);

	public int delete(Professor s);

	public Professor getById(long id);

	public List<Professor> getAll();

	public Set<Course> getCourses(long id);

}

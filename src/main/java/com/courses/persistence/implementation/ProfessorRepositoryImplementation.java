package com.courses.persistence.implementation;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.courses.model.Course;
import com.courses.model.Professor;
import com.courses.persistence.BaseRepository;
import com.courses.persistence.ProfessorRepository;

@Repository
public class ProfessorRepositoryImplementation implements ProfessorRepository {

	@Autowired
	BaseRepository baseRepository;

	public Professor save(Professor s) {
		return baseRepository.saveOrUpdate(s);
	}

	public int delete(Professor s) {
		return baseRepository.delete(Professor.class, s.getId());
	}

	public Professor getById(long id) {
		return baseRepository.getById(Professor.class, id);
	}

	public List<Professor> getAll() {
		return baseRepository.find(Professor.class, null);
	}

	public Set<Course> getCourses(long id) {
		return baseRepository.getCoursesByProfessor(id);
	}

}

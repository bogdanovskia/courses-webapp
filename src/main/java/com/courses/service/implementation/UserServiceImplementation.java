package com.courses.service.implementation;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courses.model.Course;
import com.courses.model.Professor;
import com.courses.model.Student;
import com.courses.model.User;
import com.courses.persistence.ProfessorRepository;
import com.courses.persistence.StudentRepository;
import com.courses.persistence.UserRepository;
import com.courses.service.UserService;

@Service
public class UserServiceImplementation<T extends User> implements UserService<T> {

	@Autowired
	UserRepository<T> userRepository;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	ProfessorRepository professorRepository;

	public int delete(User s) {
		return userRepository.delete(s);
	}

	public User getById(long id) {
		return userRepository.getById(id);
	}

	public List<User> getAll() {
		return userRepository.getAll();
	}

	public boolean isValidUser(String username, String password) {
		return userRepository.isValidUser(username, password);
	}

	public User getUserByUsername(String username) {
		return userRepository.getUserByUsername(username);
	}

	public Set<Course> getCourses(User u) {
		return userRepository.getCourses(u);
	}

	@SuppressWarnings("unchecked")
	public T save(T s) {
		if (s.isStudent()) {
			return (T) studentRepository.save((Student) s);
		}
		return (T) professorRepository.save((Professor) s);
	}

}

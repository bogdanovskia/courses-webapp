package com.courses.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courses.model.User;
import com.courses.persistence.UserRepository;
import com.courses.service.UserService;

@Service
public class UserServiceImplementation<T extends User> implements UserService {

	@Autowired
	UserRepository<T> userRepository;

	public User save(User s) {
		return userRepository.save(s);
	}

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

}
